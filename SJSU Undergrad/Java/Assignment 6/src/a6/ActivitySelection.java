package a6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


public class ActivitySelection {

	/**
	 * This Activity class stores an activity along
	 * with the start, finish, and its weight.
	 * @author andreopo
	 *
	 * Zander Bonnet
	 */
	class Activity implements Comparable<Activity>  {

		private final int start;
		private final int finish;
		private final int weight;

		public Activity(final int start, final int finish, final int weight) {
			this.start = start;
			this.finish = finish;
			this.weight = weight;
		}

		public int getStart() {
			return start;
		}

		public int getFinish() {
			return finish;
		}

		public int getWeight() {
			return weight;
		}

		@Override
		public boolean equals(final Object o) {
			if (!(o instanceof Activity)) {
				return false;
			}
			final Activity a = (Activity) o;
			return a.start == this.start && a.finish == this.finish && a.weight == this.weight;
		}

		@Override
		public int compareTo(final Activity activity) {
			return (this.getFinish() < activity.getFinish() ? -1 : (this.getFinish() == activity.getFinish() ? 0 : 1));
		}

		@Override
		public String toString() {
			return " Start: " + this.getStart() + ", Finish: " + this.getFinish() + ", Weight:" + this.getWeight();
		}
	}

	// All the activities that will be analyzed
	List<Activity> activities = null;

	public ActivitySelection() {
		activities = new ArrayList<Activity>();
	}

	// Add a new activity to the ActivitySelection object
	public void addActivity(final int start, final int finish, final int weight) {
		activities.add(new Activity(start, finish, weight));
	}

	public Activity returnActivity(final int start, final int finish, final int weight) {
		return new Activity(start, finish, weight);
	}

	/**
	 * Returns the maximal possible list of activities that do not overlap with each
	 * other. Implements the GREEDY activity selection algorithm as we saw it in
	 * class; either an iterative or recursive implementation is fine. The goal is
	 * to select a maximum list of activities that do not overlap with each other!
	 * 
	 * @return The list of the Activities that were selected
	 */
	public List<Activity> selectActivitiesGreedy() {
		// First sort in ascending order by the finish time
		// (How does this sorting work? See the compareTo method in the Activity class).
		Collections.sort(activities);
		final List<Activity> acts = new ArrayList<Activity>();
		/**
		 * TODO implement the rest
		 */
		final int n = activities.size();
		int i = 0;
		acts.add(activities.get(i));
		for (int j = 1; j < n; j++)
			if (activities.get(j).getStart() >= activities.get(i).getFinish()) {
				acts.add(activities.get(j));
				i = j;
			}
		return acts;
	}

	public int findlastfinishingjob(final int inds, final int indf, final int nextstart) {
		int max_fin = -1;
		int max_ind = -1;
		for (int i = inds; i <= indf; i++) {
			final int fin = activities.get(i).getFinish();
			if (fin < nextstart && fin > max_fin) {
				max_fin = fin;
				max_ind = i;
			}
		}
		return max_ind;
	}

	/**
	 * Function selectActivitiesDynamic_maxSumWeights Returns the highest sum of
	 * weights for a list of activities that do not overlap. Note: This uses the
	 * weight for each Activity. Implements the DYNAMIC activity selection
	 * algorithm. This finds the activities that maximize the sum of weights and
	 * also do not overlap with each other!
	 * 
	 * @return The highest sum of weights for any list of Activities that do not
	 *         overlap
	 */
	public int selectActivitiesDynamic_maxSumWeights() {
		// First sort in ascending order by the finish time
		// (How does this sorting work? See the compareTo method in the Activity class).
		Collections.sort(activities);

		final int n = activities.size();
		final int optimalWeightsum[] = new int[n + 1];
		optimalWeightsum[0] = 0;
		final int lastfinishingjob[] = new int[n];

		// Find the last finishing job before each job's start time.
		// The first position is -1, since there is no previous job.
		lastfinishingjob[0] = -1;
		for (int i = 1; i < n; i++) {
			// set to the index of the last job before i's start time
			lastfinishingjob[i] = findlastfinishingjob(0, i - 1, activities.get(i).getStart());
		}

		// Compute the optimal weights
		for (int j = 1; j <= n; j++) {
			final int weightsumExcluding = optimalWeightsum[j - 1];
			int weightsumIncluding = activities.get(j - 1).getWeight();
			if (lastfinishingjob[j - 1] != -1) {
				weightsumIncluding += optimalWeightsum[lastfinishingjob[j - 1] + 1];
			}
			optimalWeightsum[j] = Math.max(weightsumIncluding, weightsumExcluding);
		}

		// The nth position holds the highest sum of weights.
		return optimalWeightsum[n];
	}

	/**
	 * Function selectActivitiesDynamic_listMaxSumWeights Returns the list of
	 * activities that have the highest sum of weights and do not overlap. Note:
	 * This uses the weight for each Activity. Implements the DYNAMIC activity
	 * selection algorithm. THIS IS THE SAME AS
	 * selectActivitiesDynamic_maxSumWeights above, except this returns the actual
	 * list of activities chosen. The goal is to find the activities that maximize
	 * the sum of weights and also do not overlap with each other!
	 * 
	 * @return The list of the Activities that were chosen with the highest sum of
	 *         weights.
	 */
	public List<Activity> selectActivitiesDynamic_listMaxSumWeights() {
		// First sort in ascending order by the finish time
		// (How does this sorting work? See the compareTo method in the Activity class).
		Collections.sort(activities);

		final List<Activity> maxWeightList = new ArrayList<Activity>();

		final int n = activities.size();
		final int optimalWeightsum[] = new int[n + 1];
		optimalWeightsum[0] = 0;
		final int lastfinishingjob[] = new int[n];

		// Find the last finishing job before each job's start time.
		// The first position is -1, since there is no previous job.
		lastfinishingjob[0] = -1;
		for (int i = 1; i < n; i++) {
			// set to the index of the last job before i's start time
			lastfinishingjob[i] = findlastfinishingjob(0, i - 1, activities.get(i).getStart());
		}
		final List<Activity> maxes = new ArrayList<Activity>();

		// Compute the optimal weights
		for (int j = 1; j <= n; j++) {
			final int weightsumExcluding = optimalWeightsum[j - 1];
			int weightsumIncluding = activities.get(j - 1).getWeight();
			if (lastfinishingjob[j - 1] != -1) {
				weightsumIncluding += optimalWeightsum[lastfinishingjob[j - 1] + 1];
			}
			optimalWeightsum[j] = Math.max(weightsumIncluding, weightsumExcluding);
			/**
			 * TODO At this point you may need to keep track of some information, so in the
			 * end you can append the included Activities to the maxWeightList.
			 */
			maxes.add(activities.get(j - 1));
		}
		int total = 0;
		while (total < optimalWeightsum[n]) {
			Activity max = activities.get(0);
			for (final Activity a : maxes)
				if (a.getWeight() > max.getWeight())
					max = a;
			total += max.getWeight();
			maxes.remove(max);
			maxWeightList.add(max);
		}
		for (final Activity a : maxWeightList)
			System.out.println(a);

		return maxWeightList;
	}

	public static void main(final String[] args) {

		final ActivitySelection select = new ActivitySelection();
		/**
		 * Add a series of activities with their start, finish times and weights.
		 */
		select.addActivity(1, 48, 1000);
		select.addActivity(1, 51, 100);
		select.addActivity(2, 52, 100);
		select.addActivity(3, 53, 100);
		select.addActivity(4, 54, 100);
		select.addActivity(5, 55, 100);
		select.addActivity(6, 56, 100);
		select.addActivity(7, 57, 100);
		select.addActivity(8, 58, 100);
		select.addActivity(9, 59, 100);
		select.addActivity(10, 60, 100);
		select.addActivity(11, 61, 100);
		select.addActivity(12, 62, 100);
		select.addActivity(13, 63, 100);
		select.addActivity(14, 64, 100);
		select.addActivity(15, 65, 100);
		select.addActivity(16, 66, 100);
		select.addActivity(17, 67, 100);
		select.addActivity(18, 68, 100);
		select.addActivity(19, 69, 100);
		select.addActivity(20, 70, 100);
		select.addActivity(21, 71, 100);
		select.addActivity(22, 72, 100);
		select.addActivity(23, 73, 100);
		select.addActivity(24, 74, 100);
		select.addActivity(25, 75, 100);
		select.addActivity(26, 76, 100);
		select.addActivity(27, 77, 100);
		select.addActivity(28, 78, 100);
		select.addActivity(29, 79, 100);
		select.addActivity(30, 80, 100);
		select.addActivity(31, 81, 100);
		select.addActivity(32, 82, 100);
		select.addActivity(33, 83, 100);
		select.addActivity(34, 84, 100);
		select.addActivity(35, 85, 100);
		select.addActivity(36, 86, 100);
		select.addActivity(37, 87, 100);
		select.addActivity(38, 88, 100);
		select.addActivity(39, 89, 100);
		select.addActivity(40, 90, 100);
		select.addActivity(41, 91, 100);
		select.addActivity(42, 92, 100);
		select.addActivity(43, 93, 100);
		select.addActivity(44, 94, 100);
		select.addActivity(45, 95, 100);
		select.addActivity(46, 96, 100);
		select.addActivity(47, 97, 100);
		select.addActivity(48, 98, 100);
		select.addActivity(49, 99, 1000);
		select.addActivity(1, 10, 10);
		select.addActivity(11, 20, 10);
		select.addActivity(21, 30, 10);
		select.addActivity(31, 40, 10);
		select.addActivity(41, 50, 10);
		select.addActivity(51, 60, 10);
		select.addActivity(61, 70, 10);
		select.addActivity(71, 80, 10);
		select.addActivity(81, 90, 10);
		select.addActivity(91, 100, 10);

		/**
		 * Run the greedy activity selection, as we saw it in class. Check that the
		 * maximal number of activities that do not overlap is returned. The assert
		 * statements show you what result to expect from the greedy activity selection
		 * algorithm.
		 */
		final List<Activity> list = select.selectActivitiesGreedy();
		assertTrue(list.get(0).equals(select.returnActivity(1, 10, 10)));
		assertTrue(list.get(1).equals(select.returnActivity(11, 20, 10)));
		assertTrue(list.get(2).equals(select.returnActivity(21, 30, 10)));
		assertTrue(list.get(3).equals(select.returnActivity(31, 40, 10)));
		assertTrue(list.get(4).equals(select.returnActivity(41, 50, 10)));
		assertTrue(list.get(5).equals(select.returnActivity(51, 60, 10)));
		assertTrue(list.get(6).equals(select.returnActivity(61, 70, 10)));
		assertTrue(list.get(7).equals(select.returnActivity(71, 80, 10)));
		assertTrue(list.get(8).equals(select.returnActivity(81, 90, 10)));
		assertTrue(list.get(9).equals(select.returnActivity(91, 100, 10)));

		System.out.println("Passed 1/2");

		/**
		 * Run the dynamic activity selection, as we saw it in class. Check if the
		 * maximal sum of weights for a list of activities that do not overlap is
		 * returned. The assert statements show you what result to expect from the
		 * dynamic activity selection algorithm.
		 */
		final int maxweight = select.selectActivitiesDynamic_maxSumWeights();
		// Assert the result is the sum of weights for selection of activities
		// (1,48,1000) and (49,99,1000)
		assertEquals(maxweight, 2000);

		/**
		 * Check that the correct activity list that maximizes the sum of weights is
		 * returned.
		 */
		final List<Activity> list2 = select.selectActivitiesDynamic_listMaxSumWeights();
		//Assert the result is selection of activities (1,48,1000) and (49,99,1000)
		assertTrue(list2.get(0).equals(select.returnActivity(1,48,1000)));
		assertTrue(list2.get(1).equals(select.returnActivity(49,99,1000)));
		System.out.println("Passed 2/2");
	}

}
