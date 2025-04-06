<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Admin Dashboard</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <!-- Your custom styles here -->
        <style>
            body {
                padding-top: 56px;
                /* Adjust based on your navbar height */
            }

            .sidebar {
                height: 100vh;
                background-color: #f8f9fa;
            }
        </style>
    </head>

    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <a class="navbar-brand" href="#">Admin Dashboard</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Users</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Products</a>
                    </li>
                    <!-- Add more navbar items as needed -->
                </ul>
            </div>
        </nav>

        <!-- Content -->
        <div class="container-fluid mt-4">
            <div class="row">
                <!-- Sidebar -->
                <nav class="col-md-3 d-none d-md-block sidebar">
                    <div class="sidebar-sticky">
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link active" href="#">
                                    Dashboard
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    Users
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    Products
                                </a>
                            </li>
                            <!-- Add more sidebar items as needed -->
                        </ul>
                    </div>
                </nav>

                <!-- Main Content -->
                <!-- Main Content -->
                <main role="main" class="col-md-9 ml-sm-auto col-lg-9 px-4">
                    <h2>Welcome, Admin!</h2>

                    <!-- First Row: 4 Small Cards -->
                    <div class="row mb-4">
                        <div class="col-md-3">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Card 1</h5>
                                    <p class="card-text">Content for card 1.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Card 2</h5>
                                    <p class="card-text">Content for card 2.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Card 3</h5>
                                    <p class="card-text">Content for card 3.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Card 4</h5>
                                    <p class="card-text">Content for card 4.</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Second Row: 2 Large Cards -->
                    <div class="row mb-4">
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Large Card 1</h5>
                                    <p class="card-text">Content for large card 1.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Large Card 2</h5>
                                    <p class="card-text">Content for large card 2.</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Third Row: Map View -->
                    <div class="row">
                        <div class="col-12">
                            <iframe
                                src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d14146.172097795306!2d80.688171!3d27.576689449999996!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2sin!4v1702217826774!5m2!1sen!2sin"
                                height="450" style="border:0;" allowfullscreen="" loading="lazy"
                                referrerpolicy="no-referrer-when-downgrade" width=100%></iframe>
                        </div>
                    </div>


                </main>

            </div>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>

    </html>