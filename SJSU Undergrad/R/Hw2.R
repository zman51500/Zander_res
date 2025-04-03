library(mclust)
data("diabetes")
### Part A

s = sample(nrow(diabetes), round(nrow(diabetes)*.7))
training <- diabetes[s,]
testing <- diabetes[-s,]

#Part B

    #Logistic Regression
library(nnet)
logreg <- multinom(class~., data = training)
summary(logreg)
predLog <- predict(logreg, testing)
table(predLog, testing$class)
logRegSucess <- sum(diag(table(predLog, testing$class)))/nrow(testing)
##Parameters
#The parameters are the log odds ratio with respect to class chemical
#for every one unit change


    #Linear Discriminant Analysis
library(MASS)
ldareg <- lda(class~., data = training)
summary(ldareg)
predLDA <- predict(ldareg, testing)
table(predLDA$class, testing$class)
ldaRegSucess <- sum(diag(table(predLDA$class, testing$class)))/nrow(testing)
##Parameters
#The parameters are assumed to all have the same standard deviation
#They identified as the maximum likelihood estimate for the classes

    #Quadratic Discriminant Analysis
qdareg <- qda(class~. , data = training)
summary(qdareg)
predQDA <- predict(qdareg, testing)
table(predQDA$class, testing$class)
qdaRedSucess <- sum(diag(table(predQDA$class, testing$class)))/nrow(testing)
##Parameters
#The parameters are not assumed to all have the same standard deviation
#They identified as the maximum likelihood estimate for the classes

#Part C

x <- c(logRegSucess,ldaRegSucess,qdaRedSucess)
max(x)

#If I run the models multiple times with different samples for the training data
#The logistic model and the qda model alternate being the best one.
#It does appear that the logistic model is better more often though.
#The lda model does not preform as well as the other two.
