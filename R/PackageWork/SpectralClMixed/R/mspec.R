#' Preforms spectral clustering on mix typed data
#'
#'
#' @param z data to be clustered
#' @param k the number of clusters.
#' @param sigma vector of lower,upper bounds for sigma
#' @param c_wt the category weights, is assigned to
#' c(0.9999, 0.999, .99, seq(0.95, 0.05,-0.05), .01, 0.001, 0.0001)
#' if null.
#' @param starts the number of random starts
#' @param its the max number of iterations for the kmeans algorithm
#' @param verbose if you would like printed output during running of function
#'
#' @return list containing ct_wt the selected category weight , bt/wt_ss the between divided by the within sum of squares,tot_wt_ss the total within sum of squares,cluster the cluster assignments,data the original data
#'
#' @examples
#' data1 <- data.frame(n1 = runif(15,-1,1), n2 = runif(15,-1,1), n3 = runif(15,-1,1),c1 = factor(rep(c(1,2,3),5)))
#' data2 <- data.frame(n1 = runif(15,2,3), n2 = runif(15,2,3), n3 = runif(15,2,3),c1 = factor(rep(c(1,2,3),5)))
#' data <- rbind(data1,data2)
#' mspec(data, centers = 2)
#'
#' @export
mspec <-
  function(z,
           k=2,
           sigma = c(20,20),
           c_wt=NULL,
           starts=10,
           its=300,
           verbose=FALSE) {
    # v200908
    # uses specc algo to pick sigma 1/max(as.matrix(dist(Filter(is.numeric, z$data))))
    # calculate Gaussian kernel similarity for numeric variables
    # simple matching similarity for categorical variables
    # add the two using some weight
    # perform spectral clustering on this similarity matrix
    # take in a dataframe x where all categorical columns are coded as 'factor'

    centers=k
    if(nrow(z) <20){
      NN = floor(nrow(z)/2)
      MM = floor(nrow(z)/2)
    }else{
      NN = sigma[1]
      MM = sigma[2]
    }

    if(is.null(c_wt)){
      cat_wgts = c(0.9999, 0.999, .99, seq(0.95, 0.05,-0.05), .01, 0.001, 0.0001)
    } else{
      cat_wgts = c_wt
    }

    iterations = its

    x <- z# data in z

    x_num <- Filter(is.numeric, x) # numeric columns of x
    x_cat <- Filter(is.factor, x) # categorical columns of x
    if(ncol(x_cat)==0){print('At least one categorical varible needed, it needs to be a factor')}
    if(ncol(x_num)==0){print('At least one continuous varible needed')}
    
     # simple matching coefficient for categorical variables
    ifelse(length(x_cat) > 0,
           x_cat_dist <-
             as.matrix(daisy(x_cat)),
           x_cat_dist <- cat_wgts <- 0)
    # all entries of x_cat_dist are between 0 and 1

    #daisy calculates the simple matching coef
    #Distance between cat variables

    ifelse(length(x_num) > 0,
           num_wgts <- 1 - cat_wgts,
           x_num_dist <- num_wgts <- 0)

    # Euclidean distance for numerical
    if (length(x_num) > 0)
      x_num_dist <- as.matrix(dist(x_num))

    x_num_dist <-
      x_num_dist / max(x_num_dist) # now x_num_dist entries are between 0 and 1

    #Standardizing numerical


    # create matrix to store output
    ARI_mat_big <-
      matrix(ncol = 3, dimnames = list(NULL, c(
        'cat_wgt', 'bt/wt_ss', 'tot_wt_ss'
      ))) #For cat_wts
    kmeans_out <- list()
    for (i in 1:length(cat_wgts)) {
      if (verbose)
        print(i)
      ktmp <-
        num_wgts[i] * x_num_dist + cat_wgts[i] * x_cat_dist #Distamce matrix between cat and num
      ktmp_sorted <-
        t(apply(ktmp, 1, sort)) #left to right distance to nearest neighbor
      x_nn_0 <-
        ktmp_sorted[,-1] # resulting column 1 is distance from each point to 1st-NN, col 2 is dist to 2nd-NN, etc
      x_nn_0_col_med <-
        apply(x_nn_0, 2, median) #median distance of neighbor to itself for all points
      tmpsigA <- x_nn_0_col_med[x_nn_0_col_med > 0]
      tmpsig <-
        c(tmpsigA[NN:MM]) #median of 20th nearest neighbor set to sigma

      # matrix to store results for each sigma
      ARI_mat_small <-
        matrix(
          nrow = length(tmpsig),
          ncol =3,
          dimnames = list(NULL, c(
            'cat_wgt', 'bt/wt_ss', 'tot_wt_ss'
          ))
        ) #for sigmas
      for (j in 1:length(tmpsig)) {
        if (verbose)
          print(paste(i, '-', j))
        ka <-
          exp((-(ktmp ^ 2)) / (2 * (tmpsig[j] ^ 2))) # matrix of pairwise similarities using Gaussian kernel
        diag(ka) <-
          0 # set diagonal to 0 thus creating NJW affinity matrix A
        d <- 1 / sqrt(rowSums(ka)) # NJW D^(-1/2) matrix
        if (!any(d == Inf) &&
            !any(is.na(d)) &&
            (max(d)[1] - min(d)[1] < 10 ^ 4)) {
          # check d: no Inf, NA and max not >> min
          #Cant have too large of a range
          l <- d * ka %*% diag(d) # NJW graph Laplacian L
          xi <-
            eigs_sym(l, centers, opts = list(maxitr = 4000))$vectors # first k eigenvectors
          yi <- xi / sqrt(rowSums(xi ^ 2))
          res <-
            try(kmeans(yi, centers, iterations, nstart = starts), silent = T)
          # do kmeans on normalized eigenvectors

          ARI_mat_small[j, 'cat_wgt'] <- cat_wgts[i]
          if (is.list(res)) {
            ARI_mat_small[j, 'bt/wt_ss'] <- res$betweenss / res$tot.withinss
            ARI_mat_small[j, 'tot_wt_ss'] <- res$tot.withinss
          }

        }

      } # for (j in 1:length(tmpsig))
      ARI_mat_big <- rbind(ARI_mat_big, ARI_mat_small)
      if(is.list(res)){
        kmeans_out[[i]] <- res$cluster
      }
    }
    ARI_mat_big <- ARI_mat_big[-1,] #Should be another way

    #maxi <- ARI_mat_big[ARI_mat_big[,2] == max(ARI_mat_big[,2], na.rm = T),]
    maxi <- ARI_mat_big[which.max(ARI_mat_big[,2]),]
    if(is.null(dim(maxi))){
      maxi <- maxi
    }
    else{
      maxi <- maxi[1,]
    }

    maxk <- kmeans_out[which.max(ARI_mat_big[,2])][[1]]

    out=list("cat_wt" = maxi[1], "bt/wt_ss" = maxi[2],
             'tot_wt_ss' = maxi[3],cluster = maxk, data = z)
    class(out) <- "SpectralClMixed"
    return(out)
  }

#' Summarizes the output of mspec
#'
#' @param object the output of mspec
#'
#' @return table of the cluster sizes
#'
#' @export

summary.SpectralClMixed <- function(object,...){
  #f = output of spectral clustering
  f=object
  cate = f$cat_wt
  bt_wt_ss = f$`bt/wt_ss`
  tot = f$tot_wt_ss
  cluster_size = f$cluster
  writeLines(c("The most effective category weight is: ", cate,
               'The between divided by within sum of squares is: ', bt_wt_ss,
               'The total within sum of squares is: ',tot))
  return(table(cluster_size))
}


#' Plots the output of mspec for up to 10 varibales
#'
#' @param x the output of mspec
#'
#' @return NULL
#'
#' @export


plot.SpectralClMixed <- function(x,cols=NULL,...){
  
  cluster <- as.factor(x$cluster)
  dat      <- x$data
  d        <- ncol(dat)
  d        <- ifelse(is.null(d), 1, d)
  
  if(is.null(cols)) {
    if(d<=10){
      print(  pairs(dat, col = cluster, main = 'Cluster Memberships', ...))
      print(   ggparcoord(cbind(cluster = cluster ,x$data),mapping=ggplot2::aes(color=as.factor(cluster )),columns=2:(ncol(x$data)+1))+ 
                 theme_bw()+scale_color_discrete("Clusters",labels=levels(cluster)))
  } else{print('Dataset too big, select varaibles to plot using cols')}
    }else {
      print(  pairs(dat[,cols], col = cluster, main = 'Cluster Memberships -Selected varaibles', ...))
      
      print(    ggparcoord( cbind(cluster = cluster ,x$data[,cols]),mapping=ggplot2::aes(color=as.factor(cluster)) , columns=2:(length(cols)+1))+
      theme_bw()+scale_color_discrete("Clusters",labels=levels(cluster)) + ggtitle('Parallel Coordinate Plot - Selected varaibles'))
       
        }
  

}

