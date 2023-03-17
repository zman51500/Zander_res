#########Math 151 Project
#Authors: Zander Bonnet, Karen Lin

fuel_consuption <- read.csv('~/Desktop/Math 151/Project/MY2022 Fuel Consumption Ratings.csv')

head(fuel_consuption)
plot(head(fuel_consuption))

##########Exploratory graphs

par(mar=c(10,5,5,1))
boxplot(Fuel.Consumption.Comb..L.100.km..~ Vehicle.Class,
        data = fuel_consuption, las = 2, xlab = NULL,
        ylab = 'Combined Liters Consumed per 100km')
title("Combined Fuel Consuption Vs. Vehicle Type")
dev.off()

boxplot(Fuel.Consumption.Comb..L.100.km..~ Fuel.Type,
        data = fuel_consuption, xlab = "Fuel Type"
        , ylab = "Combined Liters Consumed per 100km")
title("Combined Fuel Consuption Vs. Fuel Type")
dev.off()

boxplot(Fuel.Consumption.Comb..mpg..~ Transmission,
        data = fuel_consuption, las = 2
        , ylab = "Combined Liters Consumed per 100km")
title("Combined Fuel Consuption Vs. Transmission Type")
dev.off()

#Prove CO2 Emissions highly correlated with fuel consumption
cor(fuel_consuption$Fuel.Consumption.Comb..L.100.km.., fuel_consuption$CO2.Emissions.g.km.)
plot(fuel_consuption$Fuel.Consumption.Comb..L.100.km.., fuel_consuption$CO2.Emissions.g.km.
     ,xlab = "Fuel Consumption", ylab = "CO2 Emissions")
title("Fuel Consumption Vs. CO2 Emissions")

##########Create model to predict fuel consumption using CO2 emssions
set.seed(10)
s <- sample(nrow(fuel_consuption), nrow(fuel_consuption) *.6)
train <- fuel_consuption[s,]
test <- fuel_consuption[-s,]

#NEAR PERFECT
t <- lm(Fuel.Consumption.Comb..L.100.km..~ CO2.Emissions.g.km., data = train)
summary(t)
pre <- predict.lm(t, test)
bad <- which(t$residuals < -1 | t$residuals > 1)# Checks what point have large and small residuals
fuel_consuption[bad,]$Model
table(fuel_consuption[bad,]$Make)
table(fuel_consuption[bad,]$Vehicle.Class)
plot(t)
errors <- test$Fuel.Consumption.Comb..L.100.km.. - pre
(MSE <- mean((test$Fuel.Consumption.Comb..L.100.km.. - pre)^2)) #MSE



##############Using forward selction to model CO2 emissions
#without fuel metrics or other co2 metricz
##Loads the data
project_data <- fuel_consuption
##Makes a copy of the data
project_data_copy <- data.frame(project_data)
project_data_copy[1:5,] ##Get rid of model , fuel consumption city
################################################################################
#####################DATA CLEANING##############################################
################################################################################
colnames(project_data)

####################Rename some columns#########################################
Fuel.Consumption.Comb.Lkm <- project_data_copy[, 11]
################################################################################


##################Delete columns we don't need#################################
##Model Year (Same for all entries) #1
##Model (kind of like a specific product) #3
##Fuel Consumption (City) #9
##Fuel Consumption (Hwy) #10
##Fuel Consumption Combined (mpg) #12


########Rename fuel consumption combined (100 km) column and delete columns we
########don't want

data_copy_subset <- subset(project_data_copy, select=-c(1,3,9,10,11,12,14,15))

################################################################################


#############Check that each variable is of correct type########################

class(data_copy_subset$Make) ##character
class(data_copy_subset$Vehicle.Class) ##character
class(data_copy_subset$Engine.Size.L) ##numeric 
class(data_copy_subset$Cylinders) ##integer
class(data_copy_subset$Transmission) ##character (not sure what is this)
class(data_copy_subset$Fuel.Type) ##character
class(data_copy_subset$Fuel.Consumption.Lkm) ##numeric
class(data_copy_subset$CO2.Emissions.g.km.) ##integer
class(data_copy_subset$Smog.Rating) ##integer

data_copy_subset[1:5,]
##################Changes data type of some variables###########################

##Changes make of the car to factor data type
make_new <- as.factor(data_copy_subset$Make) 
##Changes vehicle class to factor data type
vehicle_class_new <- as.factor(data_copy_subset$Vehicle.Class)
##Changes transmission to factor data type
transmission_new <- as.factor(data_copy_subset$Transmission)
##Changes fuel type to factor data type
fuel_type_new <- as.factor(data_copy_subset$Fuel.Type)
co2_emiss_new <- as.numeric(data_copy_subset$CO2.Emissions.g.km)
colnames(data_copy_subset)
#####Replaces old columns with these new columns
data_copy_subset2 <- subset(data_copy_subset, select=-c(1,2,5,6,7))
data_copy_subset2$Make <- make_new
data_copy_subset2$Class <- vehicle_class_new
data_copy_subset2$Transmission <- transmission_new
data_copy_subset2$Fuel.Type <- fuel_type_new
data_copy_subset2$CO2.Emissions <- co2_emiss_new



################################################################################

######################Check for missing values##################################

which(is.na(data_copy_subset2$Engine.Size.L.)==TRUE)
which(is.na(data_copy_subset2$Cylinders)==TRUE)
which(is.na(data_copy_subset2$CO2.Rating)==TRUE)
which(is.na(data_copy_subset2$Smog.Rating)==TRUE)
which(is.na(data_copy_subset2$Fuel.Consumption.Lkm)==TRUE)
which(is.na(data_copy_subset2$Make)==TRUE)
which(is.na(data_copy_subset2$Class)==TRUE)
which(is.na(data_copy_subset2$Transmission)==TRUE)
which(is.na(data_copy_subset2$Fuel.Type)==TRUE)
which(is.na(data_copy_subset2$CO2.Emissions)==TRUE)

data_copy_subset2[1:5,]



################################################################################
###################MULTIPLE LINEAR REGRESSION###################################
################################################################################

#######Splits data into training (60%) and testing (40%) subsets
set.seed(10)
training_indices <- sample(nrow(data_copy_subset2), round(nrow(data_copy_subset2)*0.6))
training_data <- data_copy_subset2[training_indices,]
testing_data <- data_copy_subset2[-training_indices,]
training_data[1:5,]

#########################Forward Selection######################################
#########Without interaction#######################
###Model with just intercept and CO2 emissions as response
fmodel_intercept <- lm(CO2.Emissions~1, data=training_data)


add1(fmodel_intercept, CO2.Emissions~Engine.Size.L.+Cylinders+Make
     +Class+Transmission+Fuel.Type, data= training_data, test="F")


##Add variable with lowest RSS (all p-values are tiny)
fmodel_1 <- lm(CO2.Emissions~Cylinders, data=training_data)




add1(fmodel_1, CO2.Emissions~Engine.Size.L.+Cylinders+Make
     +Class+Transmission+Fuel.Type, data= training_data, test="F")

##Add variable with lowest p-value and lowest RSS
fmodel_2 <- lm(CO2.Emissions~Cylinders+Make, data=training_data) 




add1(fmodel_2, CO2.Emissions~Engine.Size.L.+Cylinders+Make
     +Class+Transmission+Fuel.Type, data= training_data, test="F")

fmodel_3 <- lm(CO2.Emissions~Cylinders+Make+Transmission, data=training_data)



add1(fmodel_3,CO2.Emissions~Engine.Size.L.+Cylinders+Make
     +Class+Transmission+Fuel.Type, data= training_data, test="F")

f_model_4 <- lm(CO2.Emissions~Cylinders+Make+Transmission+Fuel.Type, data=training_data)




add1(f_model_4,CO2.Emissions~Engine.Size.L.+Cylinders+Make
     +Class+Transmission+Fuel.Type, data= training_data, test="F")

f_model_5 <- lm(CO2.Emissions~Cylinders+Make+Transmission+Fuel.Type+Class, data=training_data)




add1(f_model_5,CO2.Emissions~Engine.Size.L.+Cylinders+Make
     +Class+Transmission+Fuel.Type, data= training_data, test="F")

f_model_5 <- lm(CO2.Emissions~Cylinders+Make+Transmission+Fuel.Type+Class+Engine.Size.L., data=training_data)

##Resulting model has predictors: Cylinders, Make, Transmision, fuel type, class and enginesize

##############Check assumptions for the model resulting from####################
#######################from forwards selection##################################

##Check residuals are normally distributed
qqnorm(f_model_5$residuals)
qqline(f_model_5$residuals)
##One weird tail. Can a transformation on response fix that?

##Check residuals have constant variance
y_hat <- predict(f_model_5)
plot(y_hat, f_model_5$residuals)
##Residuals look like they generally have constant variance

##Check residuals aren't correlated with one another
plot(f_model_5$residuals)
##Residuals definitely look uncorrelated

plot(f_model_5)


###############Backwards selection#################################
##Model with all predictors we're interested in 
bmodel_1 <- lm(CO2.Emissions~., data=training_data)
drop1(bmodel_1,CO2.Emissions~Engine.Size.L.+Cylinders+Make
      +Class+Transmission+Fuel.Type, data=training_data, test="F")
##p-values are all tiny so we don't drop anything



###############Check assumptions for backwards selection############
##Check residuals are normally distributed
qqnorm(bmodel_1$residuals)
qqline(bmodel_1$residuals)
##Two pretty awful tails


##Check residuals have constant variance
y_hat2 <- predict(bmodel_1)
plot(y_hat2, bmodel_1$residuals)
##Yes?


####Check residuals aren't correlated with one another
plot(bmodel_1$residuals)
##Look uncorrelated

plot(bmodel_1)



################Model for fuel consumption without fuel or co2
mod <- lm(Fuel.Consumption.Comb..L.100.km..~ Make + Vehicle.Class
          + Engine.Size.L. + Cylinders + Transmission + Fuel.Type,
          data = train)
summary(mod)
plot(mod)
(MSEmod <- mean((test$Fuel.Consumption.Comb..L.100.km.. - predict(mod,test))^2))
drop1(mod, test = 'F')

# Explore if less variables will improve model
mod2 <- lm(Fuel.Consumption.Comb..L.100.km..~ Make + Vehicle.Class
           + Engine.Size.L. + Transmission + Fuel.Type,
           data = train)
summary(mod2)
plot(mod2)
(MSEmod2 <- mean((test$Fuel.Consumption.Comb..L.100.km.. - predict(mod2,test))^2))
drop1(mod2,  test = 'F')

mod3 <- lm(Fuel.Consumption.Comb..L.100.km..~ Make
            + Cylinders + Transmission + Fuel.Type,
           data = train)
summary(mod3)
plot(mod3)
(MSEmod3 <- mean((test$Fuel.Consumption.Comb..L.100.km.. - predict(mod3,test))^2))
drop1(mod3, test = "F")


#Having less variables does not improve the model
#Preforms worse in every metric


############Find outliers
m <- mean(fuel_consuption$Fuel.Consumption.Comb..L.100.km..)
std <- sd(fuel_consuption$Fuel.Consumption.Comb..L.100.km..)
up <- which(fuel_consuption$Fuel.Consumption.Comb..L.100.km.. > (m + 3*std)) #Upper outliers
low <- which(fuel_consuption$Fuel.Consumption.Comb..L.100.km.. < (m- 3*std)) #Lower outliers
fuel_consuption[c(up,low),]
prune <- fuel_consuption[-c(up,low),] #trims out outliers

##############Models with no outliers
set.seed(10)
s <- sample(nrow(prune), nrow(prune) *.6)
prune.train <- prune[s,]
prune.test <- prune[-s,]

#NEAR PERFECT
prune.t <- lm(Fuel.Consumption.Comb..L.100.km..~ CO2.Emissions.g.km., data = prune.train)
prune.pre <- predict.lm(prune.t, prune.test)
summary(prune.t)
plot(prune.t)
prune.bad <- which(prune.t$residuals < -1 | prune.t$residuals > 1)# checks large and small residuals
fuel_consuption[prune.bad,]$Model
table(fuel_consuption[prune.bad,]$Make)
table(fuel_consuption[prune.bad,]$Vehicle.Class)
(prune.MSE <- mean((prune.test$Fuel.Consumption.Comb..L.100.km.. - prune.pre)^2))

##########Pruned Model without fuel or co2

prune.mod <- lm(Fuel.Consumption.Comb..L.100.km..~ Make + Vehicle.Class
          + Engine.Size.L. + Cylinders + Transmission + Fuel.Type,
          data = prune.train)
summary(prune.mod)
plot(prune.mod)
(prune.MSEmod <- mean((prune.test$Fuel.Consumption.Comb..L.100.km..
                      - predict(prune.mod,prune.test))^2))
drop1(prune.mod, test = 'F')


##Checks if reducing the model improves performance or decreases performance 
prune.mod2 <- lm(Fuel.Consumption.Comb..L.100.km..~ Make + Vehicle.Class
                 + Engine.Size.L. + Transmission + Fuel.Type,
                 data = prune.train)
summary(prune.mod2)
plot(prune.mod2)
(prune.MSEmod2 <- mean((prune.test$Fuel.Consumption.Comb..L.100.km..
                      - predict(prune.mod2,prune.test))^2))
drop1(prune.mod2, test = "F")

prune.mod3 <- lm(Fuel.Consumption.Comb..L.100.km..~ Make + Vehicle.Class
                 + Engine.Size.L. + Transmission,
                 data = prune.train)
summary(prune.mod3)
plot(prune.mod3)
(prune.MSEmod3 <- mean((prune.test$Fuel.Consumption.Comb..L.100.km..
                      - predict(prune.mod3,prune.test))^2))
drop1(prune.mod3, test = "F")


#Performance decreases significantly with reduction of predictors


################ Kmeans anaylisis
d <- data.frame(fuel_consuption$Fuel.Consumption.Comb..L.100.km..,
                fuel_consuption$Cylinders,
                fuel_consuption$Engine.Size.L.,
                fuel_consuption$CO2.Emissions.g.km.)
set.seed(10)

head(d)

k <- kmeans(d,centers = 3, nstart = 10)
plot(d ,col = k$cluster)

library(cluster)
k.out <- list()
s.vals <- list()
good = NULL
for(i in 2:50){
  k.out[[i]]<- kmeans(d, centers =i, nstart = 10)
  s.vals[[i]] <- silhouette(x=k.out[[i]]$cluster,dist=dist(d))
  good[i] <- mean(s.vals[[i]][,3])
} #Creates a large range of kmeans objects to see if there is a proper amount of clusters
#to choose
good
which(good == max(good, na.rm = T)) # 2 clusters
plot(d, col = k.out[[2]]$cluster)
k.out[[2]]$centers
table(k.out[[2]]$cluster)

#Checking if clustered by number of factors in each variable
table(fuel_consuption[which(k.out[[23]]$cluster == 6),]$Transmission) 
table(fuel_consuption[which(k.out[[14]]$cluster ==9),]$Vehicle.Class)
table(fuel_consuption[which(k.out[[10]]$cluster ==9),]$Smog.Rating)
#Fails to result in anything meaningful



