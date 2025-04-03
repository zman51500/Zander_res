#1 
x <- 0
(x <- x + 1 )
# Answer: 1
# This is because the first value of x is 0
# With that in mind the second operation reads x <- 0 + 1
# Leading to the answer 1

#2
((4^3+5!=23)==4^3+5)
# Answer: FALSE
#Line 1 is testing if 4^3+5 does NOT equal 23 turning it into the logical TRUE
#Then it compares the logical from the left hand side to the solution of the right
#This then leads to TRUE == 69 which is FALSE

#3
#NA is there no value available or the value is missing
#Where NULL is when the object does not exist

#4
x <- c('01/16/2021','02/03/2021')
(x <- as.Date(x,"%m/%d/%Y"))
#Answer: "2021-01-16" "2021-02-03"

#5a
mean = 100
var = 100
std = sqrt(var)

qnorm(.9,mean,std)
qnorm(p = .9, mean = mean, sd = std)
#Answer: 112.8155

#5b
mean = 100
var = 100
std = sqrt(var)

1 - pnorm(90, mean, std)
pnorm(90,mean,std,lower.tail = FALSE)
#Answer: 0.8413447
