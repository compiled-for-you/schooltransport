<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"> -->
    <!-- Font Awesome -->
<link
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
rel="stylesheet"
/>
<!-- Google Fonts -->
<link
href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
rel="stylesheet"
/>
<!-- MDB -->
<link
href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.css"
rel="stylesheet"
/>
    <style> 
        .divider:after,
        .divider:before {
        content: "";
        flex: 1;
        height: 1px;
        background: #eee;
        }
    </style>
    <title>Login</title>
  </head>
  <body>
    <!--page code start-->
    <section class="vh-100">
        <div class="container py-5 h-100">
          <div class="row d-flex align-items-center justify-content-center h-100">
            <div class="col-md-8 col-lg-7 col-xl-6">
              <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
                class="img-fluid" alt="Phone image">
            </div>
            <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
              <form>
                <!-- Email input -->
                <div class="form-outline mb-4">
                  <input type="email" id="form1Example13" class="form-control form-control-lg" />
                  <label class="form-label" for="form1Example13">Email address</label>
                </div>
      
                <!-- Password input -->
                <div class="form-outline mb-4">
                  <input type="password" id="form1Example23" class="form-control form-control-lg" />
                  <label class="form-label" for="form1Example23">Password</label>
                </div>
      
                <div class="d-flex justify-content-around align-items-center mb-4">
                  <!-- Checkbox -->
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="form1Example3" />
                    <label class="form-check-label" for="form1Example3"> Remember me </label>
                  </div>
                  <a href="#!">Forgot password?</a>
                </div>
      
                <!-- Submit button -->
                <button type="submit" class="btn btn-primary btn-lg btn-block">Sign in</button>
      
                <div class="divider d-flex align-items-center my-4">
                  <p class="text-center fw-bold mx-3 mb-0 text-muted">OR</p>
                </div>
      
                <div class="btn btn-primary btn-lg btn-block" style="background-color: #3b5998"
                  role="button" id="fb">
                  <i class="fab fa-facebook-f me-2"></i>Continue with Facebook
              </div>
                <div class="btn btn-primary btn-lg btn-block" style="background-color: #55acee"
                  role="button" id="twitter">
                  <i class="fab fa-twitter me-2"></i>Continue with Twitter</div>
      
              </form>
            </div>
          </div>
          <div class="d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-primary">
            <!-- Copyright -->
            <div class="text-white mb-3 mb-md-0">
              Copyright © 2023. All rights reserved.
            </div>
            <!-- Copyright -->
        
            <!-- Right -->
            <div>
              <a href="#!" class="text-white me-4">
                <i class="fab fa-facebook-f"></i>
              </a>
              <a href="#!" class="text-white me-4">
                <i class="fab fa-twitter"></i>
              </a>
              <a href="#!" class="text-white me-4">
                <i class="fab fa-google"></i>
              </a>
              <a href="#!" class="text-white">
                <i class="fab fa-linkedin-in"></i>
              </a>
            </div>
            <!-- Right -->
          </div>
        </div>
        
  
      </section>
    <!--page code end-->
    <!-- Option 1: Bootstrap Bundle with Popper -->
    <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script> -->
    <!-- MDB -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.js"></script>
    <script src="/js/home.js"></script>
  </body>
</html>