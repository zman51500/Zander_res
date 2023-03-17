#source('/Users/zanderbonnet/Desktop/Research Class/200405 gen_mixed_data function.R')
#source('/Users/zanderbonnet/Desktop/Research Class/200908 speclus_v_200708 fx kpr function.r')
#x <- gen_mixed_data(num_min_max = 1)
x<-gen_mixed_data(num_min_max = 5, cat_overlap = 3)
x<-data_4800[["high_high"]][['2_2']][["3"]][["200_200"]][[15]][["dataset"]]

x <- data.frame(n1 = runif(15,-1,1), n2 = runif(15,-1,1), n3 = runif(15,-1,1),c1 = factor(rep(c(1,2,3),5)))
(f <- fx2Final(x, centers = 2))
fx.summary(f)
pairs(x$data, col = x$lbl)

x<-gen_mixed_data(num_min_max = 5)
t<-fx2(x, centers =2)$ARI_mat_big
t[t[,3]==max(t[,3]),]




#########
s1TimeList = NULL
s2TimeList = NULL
s3TimeList = NULL
s4TimeList = NULL
ARI1List = NULL
ARI2List = NULL
ARI3List = NULL
ARI4List = NULL
broken = NULL
brokenX = NULL
for(i in c(1:10)){
  cat('itteration: ', i , '\n')
  x <- data_4800[["low_high"]][['3_1']][["3"]][["200_200"]][[i]][["dataset"]]
  pairs(x)
  s1 <- proc.time()
  r1 <- fx(x, centers = 2)
  s1 <- proc.time() - s1
  r1 = na.omit(r1$ARI_mat_big)
  best1 = r1[r1[,3] == max(r1[,3]),]
  if(is.null(dim(best1))){
    print(best1[5])
    if(best1[5]>1){
      broken = append(broken, r1)
      brokenX = append(brokenX, x)
      print(r1)
      break
    }
    ARI1List[i] = best1[5]
  } else{
    print(best1[1,5])
    ARI1List[i] = best1[1,5]
    if(best1[1,5]>1){
      broken =  append(broken, r1)
      brokenX = append(brokenX, x)
      print(r1)
      break
    }
  }
  s1TimeList[i] =  s1[1]



  s2 <- proc.time()
  r2 <- fx2(x, centers = 2)
  s2 <- proc.time() - s2
  r2 = na.omit(r2$ARI_mat_big)
  best2 = r2[r2[,3] == max(r2[,3]),]
  if(is.null(dim(best2))){
    print(best2[5])
    ARI2List[i] = best2[5]
    if(best2[5]>1){
      broken = append(broken, r2)
      brokenX = append(brokenX, x)
      print(r2)
      break
    }
  }else{
    print(best2[1,5])
    ARI2List[i] = best2[1,5]
    if(best2[1,5]>1){
      broken = append(broken, r2)
      brokenX = append(brokenX, x)
      print(r2)
      break
    }
  }
  s2TimeList[i] = s2[1]


  s3 <- proc.time()
  r3 <- fx3(x, centers = 2)
  s3 <- proc.time() - s3
  r3 = na.omit(r3$ARI_mat_big)
  best3 = r3[r3[,3] == max(r3[,3]),]
  if(is.null(dim(best3))){
    print(best3[5])
    ARI3List[i] = best3[5]
    if(best3[5]>1){
      broken = append(broken, r3)
      brokenX = append(brokenX, x)
      print(r3)
      break
    }
  }else{
    print(best3[1,5])
    ARI3List[i] = best3[1,5]
    if(best3[1,5]>1){
      broken = append(broken, r3)
      brokenX = append(brokenX, x)
      print(r3)
      break
    }
  }
  s3TimeList[i] = s3[1]


  s4 <- proc.time()
  r4 <- fx4(x, centers = 2)
  s4 <- proc.time() - s4
  r4 = na.omit(r4$ARI_mat_big)
  best4 = r4[r4[,3] == max(r4[,3]),]
  if(is.null(dim(best4))){
    print(best4[5])
    ARI4List[i] = best4[5]
    if(best4[5]>1){
      broken =  append(broken, r4)
      brokenX = append(brokenX, x)
      print(r4)
      break
    }
  }else{
    print(best4[1,5])
    ARI4List[i] = best4[1,5]
    if(best4[1,5]>1){
      broken = append(broken, r4)
      brokenX = append(brokenX, x)
      print(r4)
      break
    }
  }
  s4TimeList[i] = s4[1]
}
(s1TimeAv = sum(s1TimeList)/length(s1TimeList))
(s2TimeAv = sum(s2TimeList)/length(s2TimeList))
(s3TimeAv = sum(s3TimeList)/length(s3TimeList))
(s4TimeAv = sum(s4TimeList)/length(s4TimeList))
(ARI1Av = sum(ARI1List)/length(ARI1List))
(ARI2Av = sum(ARI2List)/length(ARI2List))
(ARI3Av = sum(ARI3List)/length(ARI3List))
(ARI4Av = sum(ARI4List)/length(ARI4List))


#fx(x)
#fx2(x)
#fx3(x)
#fx4(x)

