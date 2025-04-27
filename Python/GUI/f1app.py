import fastf1 as f1
import numpy as np
import pandas as pd
from dash import Dash, dcc, html, Input, Output
from dash.exceptions import PreventUpdate
import plotly.express as px

app = Dash(__name__)

# Load event schedule and session data
events = f1.get_event_schedule(2025, include_testing=False)
# Filter events to only include those that have occurred
comp = events.iloc[np.where(
    pd.to_datetime('today', utc=True) > pd.to_datetime(events['Session5DateUtc'], utc=True)
)]['EventName']
# Get the first event in the list
session = f1.get_session(2025, 'Australia', 'R')
session.load(telemetry=False, weather=False)

# Define Dash app layout
app.layout = html.Div(
    style={'backgroundColor': '#111111', 'color': '#7FDBFF'},
    children=[
        html.H1(
            children='2025 F1 Place Chart',
            className='header-title',
            style={'textAlign': 'center'}
        ),
        html.Div(
            [
                # Create a list for selecting Race
                dcc.Markdown(children='Select Race to display.'),
                dcc.RadioItems(
                    id='Race',
                    options=[{'label': event, 'value': event} for event in comp],
                    value=comp.iloc[0],
                    inline=True,
                )
            ],
            style={
                'width': '100%',
                'display': 'inline-block',
                'textAlign': 'center',
                'backgroundColor': '#111111',
                'color': '#7FDBFF'
            }
        ),
        dcc.Markdown(
            children='Select the drivers to display in the place chart.',
            style={'textAlign': 'center'}
        ),
        html.Div(
            [
                # Create a dropdown for selecting Drivers
                dcc.Dropdown(
                    id="Driver",
                    options=[driver for driver in session.laps['Driver'].unique()],
                    value=[driver for driver in session.laps['Driver'].unique()],
                    placeholder='Select a driver',
                    multi=True,
                    style={
                        'width': '100%',
                        'textAlign': 'center',
                        'backgroundColor': '#111111',
                        'color': '#7FDBFF'
                    },
                    className='justify-content-center',
                    clearable=True,
                    searchable=True,
                    maxHeight=200
                )
            ],
            style={
                'width': '100%',
                'textAlign': 'center',
                'backgroundColor': '#111111',
                'color': '#7FDBFF'
            },
            className='justify-content-center'
        ),
        #plotly graph
        dcc.Graph(
            id="Plot",
            figure={},
            style={
                'height': '70vh',
                'width': '100%',
                'textAlign': 'center',
                'backgroundColor': '#111111',
                'color': '#7FDBFF'
            }
        )
    ]
)

# Define callback to update the driver dropdown
@app.callback(
    Output(component_id="Driver", component_property="options"),
    Input(component_id="Race", component_property="value")
)
def update_drivers(menu):
    if not menu:
        raise PreventUpdate
    # Load the session data for the selected race
    session = f1.get_session(2025, menu, 'R')
    session.load(telemetry=False, weather=False)
    # Update the driver options based on race selection
    return [driver for driver in session.laps['Driver'].unique()]

# Define callback to update the graph
@app.callback(
    Output(component_id="Plot", component_property="figure"),
    Input(component_id="Driver", component_property="value"),
    Input(component_id="Race", component_property="value"),
)
def update_graph(drivers, menu):
    if not menu:
        raise PreventUpdate
    # Load the session data for the race selected
    session = f1.get_session(2025, menu, 'R')
    session.load(telemetry=False, weather=False)
    # If no drivers are selected, return a blank figure
    if not drivers:
        return gen_blank_fig(session)
    #filter by the selected drivers
    filt = session.laps.pick_drivers(drivers)
    #generate the place chart
    return gen_place_chart(session,filt)

# Generate a blank figure
def gen_blank_fig(session):
    fig = px.scatter(title="No drivers selected")
    fig.update_layout(
        xaxis_title='Lap Number',
        yaxis_title='Position',
        title_x=0.5,
        title_y=0.95,
        legend_title_text='Driver',
        paper_bgcolor='#111111',
        font_color='#7FDBFF'
    )
    fig.update_yaxes(
        dtick=1,
        range=[0, session.laps['Position'].max()],
        title_standoff=10
    )
    fig.update_xaxes(
        dtick=1,
        range=[0, session.laps['LapNumber'].max() + 1],
        title_standoff=10
    )
    return fig

# Generate the place chart
def gen_place_chart(ses, filt):
    fig = px.scatter(
        filt,
        x='LapNumber',
        y='Position',
        color='Driver',
        hover_name='Driver',
        title=f"{ses.event['EventName']} F1 Place Chart"   
    )
    fig.update_traces(
        mode='lines+markers',
        marker=dict(size=5),
        line=dict(width=2)
    )
    fig.update_layout(
        xaxis_title='Lap Number',
        yaxis_title='Position',
        title_x=0.5,
        title_y=0.95,
        legend_title_text='Driver',
        paper_bgcolor='#111111',
        font_color='#7FDBFF'
    )
    fig.update_yaxes(
        dtick=1,
        range=[0, filt['Position'].max() + 1],
        title_standoff=10
    )
    fig.update_xaxes(
        dtick=1,
        range=[0, ses.laps['LapNumber'].max() + 1],
        title_standoff=10
    )
    return fig

if __name__ == "__main__":
    app.run(debug=False, port=8050)