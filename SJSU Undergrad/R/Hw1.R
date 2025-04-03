##1##

#a
plot(CarSel)
mod <- lm(Price ~.,data =CarSel)
summary(mod)
par(mfrow = c(2,2))
plot(mod)
anova(mod)
# Models errors are roughly normal
#There is a  pattern in the residuals vs predicted plot
#Not centered around 0
#R^2 shows that the model is linear

#b
mod2 <- lm((Price^(-.5))~.,data = CarSel)
summary(mod2)
par(mfrow = c(2,2))
plot(mod2)
anova(mod2)
# Models errors are roughly normal
#There is no pattern between the residuals and the fitted values
#They are also centered around 0
#R^2 shows that the model is linear

#c
#The residuals in the second model are much tighter than in the first
#They appear to be more randomly dispersed as well
#They fit the Q-Q plot better as well so they fit the normality
#   assumption better


#d
res9 = lm((Price^(-.5))~., data = CarSel)
drop1(res9,sqrt(Price)~., data = CarSel, test = "F")

res8 <- lm((Price^(-.5))~. - Cylinders, data = CarSel)
drop1(res8,sqrt(Price)~., data = CarSel, test = "F")

res7 <- lm((Price^(-.5))~. - Cylinders - EngineSize, data = CarSel)
drop1(res7,sqrt(Price)~., data = CarSel, test = "F")

res6 <- lm((Price^(-.5))~. - Cylinders - EngineSize - MPG.highway, data = CarSel)
drop1(res6,sqrt(Price)~., data = CarSel, test = "F")

res5 <- lm((Price^(-.5))~. - Cylinders - EngineSize - MPG.highway
           - Weight, data = CarSel)
drop1(res5,sqrt(Price)~., data = CarSel, test = "F")

summary(res5)

#Model = 2.493e-01 - 9.898e-03(TypeLarge) - 1.177e-02(TypeMidsize)
#         + 3.108e-02(TypeSmall) + 8.641e-03(TypeSporty) 
#         - 6.533e-03(TypeVan) + 2.157e-03(MPG.city)
#         + 5.427e-03(AirBagsDriver_only) + 2.980e-02(AirBagsNone) 
#         - 4.201e-04(Horsepower) - 1.964e-02(Orginnon-USA)



##2##

#a

mod3 <- lm((Price^-.5) ~ Weight + Horsepower + Origin 
           + Origin:Horsepower, data = CarSel)
summary(mod3)

#b
#USCarsModel = 4.344e-01 - 4.280e-05(Weight) - 3.530e-04(Horsepower)

#NonUSCarsModel = 0.465575 - 4.280e-05(Weight) - 3.530e-04(Horsepower)


