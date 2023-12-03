<!doctype html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Bootstrap CSS -->
  <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"> -->
  <!-- Font Awesome -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />
  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet" />
  <!-- MDB -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.css" rel="stylesheet" />

  <link rel="stylesheet" href="/css/register.css">
  <style>
    .divider:after,
    .divider:before {
      content: "";
      flex: 1;
      height: 1px;
      background: #eee;
    }
  </style>
  <title>Register Page</title>
</head>

<body>
  <!--page code start-->
  <section class="vh-100">
    <div
      class="fixed-top d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-primary">
      <!-- Copyright -->
      <div class="text-white mb-3 mb-md-0">
        TRANS PAN
      </div>
    </div>
    <form class="container h-100" id="userRegistrationForm">
      <div class="container h-100" id="commonForm">
        <div class="row d-flex align-items-center justify-content-center h-100">
          <div class="col-md-8 col-lg-7 col-xl-6">
            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
              class="img-fluid" alt="Phone image">
          </div>
          <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
            <!-- Username input -->
            <div class="form-outline mb-4">
              <input type="text" id="username" name="username" class="form-control form-control-lg" />
              <label class="form-label" for="form1Example13">Create Username</label>
            </div>

            <!-- Password input -->
            <div class="form-outline mb-4">
              <input type="password" id="password" name="password" class="form-control form-control-lg" />
              <label class="form-label" for="form1Example23">Create Password</label>
            </div>

            <div class="form-outline mb-4">
              <input type="password" id="passwordCnf" class="form-control form-control-lg" />
              <label class="form-label">Confirm Password</label>
            </div>

            <div class="divider d-flex align-items-center my-4">
              <p class="text-center fw-bold mx-3 mb-0 text-muted">Register As</p>
            </div>

            <div class="icon-container">
              <div class="icon" id="organizationIcon" onclick="selectUserType('organization')">
                <!-- Replace the class with your organization icon -->
                <i class="fa-solid fa-building-columns"></i>
                <span>Organization</span>
              </div>

              <div class="icon" id="studentIcon" onclick="selectUserType('student')">
                <!-- Replace the class with your student icon -->
                <i class="fa-solid fa-graduation-cap"></i>
                <span>Student</span>
              </div>

              <div class="icon" id="driverIcon" onclick="selectUserType('driver')">
                <!-- Replace the class with your driver icon -->
                <i class="fa-solid fa-van-shuttle"></i>
                <span>Driver</span>
              </div>
            </div>
            <button type="button" id="nextBtn" class="btn btn-primary btn-lg btn-block" disabled>Next</button>
          </div>
        </div>
      </div>
      <div class="container h-100" id="userSpecificForm" hidden>
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
            <div id="organizationRegistrationForm" hidden>
              <!--org form start-->
              <div class="form-outline mb-4">
                <input type="text" class="form-control form-control-lg" name="orgName" id="orgName">
                <label for="orgName" class="form-label">Organization Name</label>
              </div>
              <div class="form-outline mb-4">
                <input type="text" class="form-control form-control-lg" name="ownerName" id="ownerName" />
                <label for="ownerName" class="form-label">Owner Name</label>
              </div>
              <div class="form-outline mb-4">
                <textarea class="form-control form-control-lg" name="address" id="address" placeholder="1234 Main St"></textarea>
                <label for="address" class="form-label">Address</label>
              </div>
              <div class="form-outline mb-4">
                <input type="number" class="form-control form-control-lg" name="contactNumber" id="contactNumber">
                <label for="contactNumber" class="form-label">Contact Number</label>
              </div>
              <div class="mb-3">
                <select class="form-select form-control-lg form-floating" name="orgType" id="orgType" >
                  <option selected>Choose Organization Type..</option>
                  <option value="School">School</option>
                  <option value="PrivateTransportProvider">Private Transport Provider</option>
                </select>
              </div>
              <button type="button" id="registerBtn" class="btn btn-primary btn-lg btn-block" onclick="postData()">Register</button>
              <!--org form end-->
            </div>
            <div id="studentRegistrationForm" hidden>
              <div class="form-outline mb-4">
                <input type="text" class="form-control form-control-lg" name="studentName" id="studentName">
                <label for="studentName" class="form-label">Student Name</label>
              </div>
              <div class="form-outline mb-4">
                <input type="text" class="form-control form-control-lg" name="grade" id="grade" />
                <label for="grade" class="form-label">Grade</label>
              </div>
              <div class="form-outline mb-4">
                <textarea class="form-control form-control-lg" name="address" id="address" placeholder="1234 Main St"></textarea>
                <label for="address" class="form-label">Address</label>
              </div>
              <div class="form-outline mb-4">
                <input type="text" class="form-control form-control-lg" name="parentName" id="parentName">
                <label for="parentName" class="form-label">Parent's Name</label>
              </div>
              <div class="form-outline mb-4">
                <input type="email" class="form-control form-control-lg" name="email" id="email">
                <label for="email" class="form-label">Parent's Email</label>
              </div>
              <div class="form-outline mb-4">
                <input type="number" class="form-control form-control-lg" name="contact" id="contact">
                <label for="contact" class="form-label">Parent's Contact</label>
              </div>
              <div class="form-outline mb-4">
                <input type="number" class="form-control form-control-lg" name="orgId" id="orgId">
                <label for="orgId" class="form-label">Enter Organization ID Linked To </label>
              </div>
              <button type="button" id="registerBtn" class="btn btn-primary btn-lg btn-block" onclick="postData()">Register</button>
            </div>
            <div id="driverRegistrationForm" hidden>
              <div class="form-outline mb-4">
                <input type="text" class="form-control form-control-lg" name="driverName" id="driverName">
                <label for="driverName" class="form-label">Driver Name</label>
              </div>
              <div class="form-outline mb-4">
                <input type="text" class="form-control form-control-lg" name="licenseNumber" id="licenseNumber" />
                <label for="licenseNumber" class="form-label">License Number</label>
              </div>
              <div class="form-outline mb-4">
                <input type="number" class="form-control form-control-lg" name="contactNumber" id="contactNumber">
                <label for="contactNumber" class="form-label">Contact Number</label>
              </div>
              <div class="form-outline mb-4">
                <textarea class="form-control form-control-lg" name="address" id="address" placeholder="1234 Main St"></textarea>
                <label for="address" class="form-label">Address</label>
              </div>
              <div class="form-outline mb-4">
                <input type="number" class="form-control form-control-lg" name="orgId" id="orgId">
                <label for="orgId" class="form-label">Enter Organization ID Linked To </label>
              </div>
              <button type="button" id="registerBtn" class="btn btn-primary btn-lg btn-block" onclick="postData()">Register</button>
            </div>
          </div>
          <div class="col-md-9 col-lg-6 col-xl-5 mb-8">
            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
              class="img-fluid" alt="Sample image">
          </div>
        </div>
      </div>
    </form>
    <div
      class="d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-primary">
      <!-- Copyright -->
      <div class="text-white mb-3 mb-md-0">
        Copyright © 2023. All rights reserved.
      </div>
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
  </section>
  <!--page code end-->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.js"></script>
  <script src="/js/register.js"></script>
</body>

</html>