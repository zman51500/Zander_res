####1

library(palmerpenguins)
pens <- na.omit(penguins)
s <- sample(nrow(pens), nrow(pens)*.7)
training <- pens[s,]
testing <- pens[-s,]

#a
linReg <- lm(body_mass_g~bill_length_mm + bill_depth_mm, data = training)
summary(linReg)
#plot(linReg)

# The residuals vs. fitted values are not random So there is a violation of constant variance
#R^2 is very 
#Q-Q plot is not linear at the tails so errors are not normally distributed


linPred <- predict(linReg,testing)
(linMSE <- sum((testing$body_mass_g-linPred)^2)/nrow(testing))

#b
library(regpro)
train1 <- cbind(training$bill_length_mm, training$bill_depth_mm)
train2 <- training$body_mass_g
nparReg <- pcf.kernesti(train1,train2, h =.8, N= c(40,40))


test <- cbind(cbind(testing$bill_length_mm, testing$bill_depth_mm))
nparMSE <- NULL
hs <- seq(0,2, by = .2)
for(j in 1:length(hs)){ #Tuning the value of h
  pred <- NULL
  for(i in 1:nrow(test)){
    pred[i] <- kernesti.regr(test[i,],train1,train2,h=hs[j])
  }
  nparMSE[j] <- sum((testing$body_mass_g-pred)^2)/nrow(testing)
}
plot(hs, nparMSE)

nparMSE[4] #First value of h before it tapers off. Value is .8

####c
#In the end the nonparametric regression preforms much better than the linear 
#model. This makes sense because the linear model didn't meet the
#assumptions


##3
library(mclust)
library(tree)
#a
tree.diab <- tree(class~., diabetes)
cv.diab <- cv.tree(tree.diab, FUN= prune.tree)
plot(cv.diab$size, cv.diab$dev, type = "b")
prune.diab <- prune.tree(tree.diab, best = 3)
plot(prune.diab)
text(prune.diab, pretty = 0)

#b
pred <- NULL
for (i in 1:nrow(diabetes)){
  if(diabetes[i,3] > 420.5){
    if(diabetes[i,2] > 117){
      pred[i] = "Overt"
    }
    else{
      pred[i] = "Chemical"
    }
  }
  else{
    pred[i] = "Normal"
  }
}
sum(diabetes$class == pred)/length(pred)


#The model does a very good job at predicting the class of diabetes for the data
#It is saying that for this data if the insulin value is less than 420.5
#you dont have diabetes. If you do though and your glucose level is 
#above 117 you have overt diabetes, but if it less than that you have 
#chemical diabetes
