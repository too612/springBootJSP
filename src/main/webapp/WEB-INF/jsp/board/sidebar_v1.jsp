<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="auto">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
  <meta name="generator" content="Hugo 0.118.2">
  <title>sidebar_v1</title>
  <!-- Script para el tema oscuro -->
  <script src="resources/js/color-modes.js"></script>
  <!-- Bootstrao 5.3.2 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <!-- Iconos de bootstrap -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
  <!-- Estilos Basicos -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/chartist.js/latest/chartist.min.css">
  <!-- Jquery -->
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"
    integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="resources/css/boardStyle.css">

  <style>
    body {
      height: 100%;
    }

    aside {
      /* border: 1px yellow solid; */
      position: fixed;
      overflow: auto;
      height: calc(100vh - 68px);
      justify-content: flex-start;
      align-self: flex-start;

    }

    main {
      position: relative;
      overflow: auto;
      margin-left: auto;
      justify-content: flex-end;
      align-self: flex-end;
    }

    #sidebarshow {
      display: none;

    }

    @media screen and (max-width: 575px) {
      #sidebarshow {
        display: inline;
      }

      #sidebartoggle {
        display: none;
      }
    }
  </style>


  <!-- Custom styles for this template -->

</head>

<body class="bg-body-tertiary">

  <svg xmlns="http://www.w3.org/2000/svg" class="d-none">
    <symbol id="check2" viewBox="0 0 16 16">
      <path
        d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z" />
    </symbol>
    <symbol id="circle-half" viewBox="0 0 16 16">
      <path d="M8 15A7 7 0 1 0 8 1v14zm0 1A8 8 0 1 1 8 0a8 8 0 0 1 0 16z" />
    </symbol>
    <symbol id="moon-stars-fill" viewBox="0 0 16 16">
      <path
        d="M6 .278a.768.768 0 0 1 .08.858 7.208 7.208 0 0 0-.878 3.46c0 4.021 3.278 7.277 7.318 7.277.527 0 1.04-.055 1.533-.16a.787.787 0 0 1 .81.316.733.733 0 0 1-.031.893A8.349 8.349 0 0 1 8.344 16C3.734 16 0 12.286 0 7.71 0 4.266 2.114 1.312 5.124.06A.752.752 0 0 1 6 .278z" />
      <path
        d="M10.794 3.148a.217.217 0 0 1 .412 0l.387 1.162c.173.518.579.924 1.097 1.097l1.162.387a.217.217 0 0 1 0 .412l-1.162.387a1.734 1.734 0 0 0-1.097 1.097l-.387 1.162a.217.217 0 0 1-.412 0l-.387-1.162A1.734 1.734 0 0 0 9.31 6.593l-1.162-.387a.217.217 0 0 1 0-.412l1.162-.387a1.734 1.734 0 0 0 1.097-1.097l.387-1.162zM13.863.099a.145.145 0 0 1 .274 0l.258.774c.115.346.386.617.732.732l.774.258a.145.145 0 0 1 0 .274l-.774.258a1.156 1.156 0 0 0-.732.732l-.258.774a.145.145 0 0 1-.274 0l-.258-.774a1.156 1.156 0 0 0-.732-.732l-.774-.258a.145.145 0 0 1 0-.274l.774-.258c.346-.115.617-.386.732-.732L13.863.1z" />
    </symbol>
    <symbol id="sun-fill" viewBox="0 0 16 16">
      <path
        d="M8 12a4 4 0 1 0 0-8 4 4 0 0 0 0 8zM8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0zm0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13zm8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5zM3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8zm10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0zm-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0zm9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707zM4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708z" />
    </symbol>
  </svg>

  <svg xmlns="http://www.w3.org/2000/svg" class="d-none">
    <symbol id="bootstrap" viewBox="0 0 118 94">
      <title>Bootstrap</title>
      <path fill-rule="evenodd" clip-rule="evenodd"
        d="M24.509 0c-6.733 0-11.715 5.893-11.492 12.284.214 6.14-.064 14.092-2.066 20.577C8.943 39.365 5.547 43.485 0 44.014v5.972c5.547.529 8.943 4.649 10.951 11.153 2.002 6.485 2.28 14.437 2.066 20.577C12.794 88.106 17.776 94 24.51 94H93.5c6.733 0 11.714-5.893 11.491-12.284-.214-6.14.064-14.092 2.066-20.577 2.009-6.504 5.396-10.624 10.943-11.153v-5.972c-5.547-.529-8.934-4.649-10.943-11.153-2.002-6.484-2.28-14.437-2.066-20.577C105.214 5.894 100.233 0 93.5 0H24.508zM80 57.863C80 66.663 73.436 72 62.543 72H44a2 2 0 01-2-2V24a2 2 0 012-2h18.437c9.083 0 15.044 4.92 15.044 12.474 0 5.302-4.01 10.049-9.119 10.88v.277C75.317 46.394 80 51.21 80 57.863zM60.521 28.34H49.948v14.934h8.905c6.884 0 10.68-2.772 10.68-7.727 0-4.643-3.264-7.207-9.012-7.207zM49.948 49.2v16.458H60.91c7.167 0 10.964-2.876 10.964-8.281 0-5.406-3.903-8.178-11.425-8.178H49.948z">
      </path>
    </symbol>
  </svg>
  <!-- <button class="btn btn-outline-secondary" id="sidebarToggle"><i class="bi bi-arrows-expand-vertical"></i></button> -->

  <nav class="navbar sticky-top navbar-expand-lg border-bottom bg-body-tertiary">
    <div class="container-fluid">

      <button class="btn btn-outline-secondary" type="button" data-bs-toggle="collapse"
        data-bs-target="#collapseWidthExample" aria-expanded="true" aria-controls="collapseWidthExample"
        style="margin-right: 10px; padding: 0px 5px 0px 5px;" id="sidebartoggle" onclick="changeclass()"> <i
          class="bi bi-arrows-expand-vertical"></i>
      </button>
      <button class="btn btn-outline-secondary" type="button" data-bs-toggle="offcanvas"
        data-bs-target="#offcanvasExample" aria-controls="offcanvasExample"
        style="margin-right: 10px; padding: 2px 6px 2px 6px;" id="sidebarshow">
        <i class="bi bi-arrow-bar-right"></i>
      </button>
      <a class="navbar-brand" href="#">Navbar</a>

      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="true" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="true">
              Dropdown
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">Action</a></li>
              <li><a class="dropdown-item" href="#">Another action</a></li>
              <li>
                <hr class="dropdown-divider">
              </li>
              <li><a class="dropdown-item" href="#">Something else here</a></li>
            </ul>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" aria-disabled="true">Disabled</a>
          </li>
        </ul>
        <form class="d-flex" role="search">
          <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success" type="submit">Search</button>
        </form>

        <!-- Cambair Tema -->
        <div class="dropdown-center" style="padding-left: 10px;">
          <button class="btn btn-bd-primary py-2 dropdown-toggle d-flex align-items-center" id="bd-theme" type="button"
            aria-expanded="false" data-bs-toggle="dropdown" aria-label="Toggle theme (auto)">
            <svg class="bi my-1 theme-icon-active" width="1em" height="1em">
              <use href="#circle-half"></use>
            </svg>
            <span class="visually-hidden" id="bd-theme-text">Toggle theme</span>
          </button>
          <ul class="dropdown-menu dropdown-menu-end shadow" aria-labelledby="bd-theme-text">
            <li>
              <button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="light"
                aria-pressed="false">
                <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em">
                  <use href="#sun-fill"></use>
                </svg>
                Light
                <svg class="bi ms-auto d-none" width="1em" height="1em">
                  <use href="#check2"></use>
                </svg>
              </button>
            </li>
            <li>
              <button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="dark"
                aria-pressed="false">
                <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em">
                  <use href="#moon-stars-fill"></use>
                </svg>
                Dark
                <svg class="bi ms-auto d-none" width="1em" height="1em">
                  <use href="#check2"></use>
                </svg>
              </button>
            </li>
            <li>
              <button type="button" class="dropdown-item d-flex align-items-center active" data-bs-theme-value="auto"
                aria-pressed="true">
                <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em">
                  <use href="#circle-half"></use>
                </svg>
                Auto
                <svg class="bi ms-auto d-none" width="1em" height="1em">
                  <use href="#check2"></use>
                </svg>
              </button>
            </li>
          </ul>
        </div>
        <!-- Cambair Tema -->
      </div>
    </div>
  </nav>


  <aside class="collapse show collapse-horizontal col-sm-2 p-3 border-end bg-body-tertiary" id="collapseWidthExample">
    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
      <svg class="bi pe-none me-2" width="40" height="32">
        <use xlink:href="#bootstrap" />
      </svg>
      <span class="d-print-block">Sidebar</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
        <a href="#" class="nav-link link-body-emphasis" aria-current="page">
          <p class="bi bi-house-door" style="position:fixed;"> Home</p><br>
        </a>
      </li>
      <li>
        <a href="#" class="nav-link link-body-emphasis">
          <p class="bi bi-speedometer2" style="position:fixed;"> Dashboard</p><br>
        </a>
      </li>
      <li>
        <a href="#" class="nav-link link-body-emphasis">
          <p class="bi bi-table" style="position:fixed;"> Orders</p><br>
        </a>
      </li>
      <li>
        <a href="#" class="nav-link link-body-emphasis">
          <p class="bi bi-grid" style="position:fixed;"> Products</p><br>
        </a>
      </li>
      <li>
        <a href="#" class="nav-link link-body-emphasis">
          <p class="bi bi-person-circle" style="position:fixed;"> Customers</p><br>
        </a>
      </li>
    </ul>
    <hr>
    <ul class="list-unstyled ps-0">
      <li class="mb-1">
        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed"
          data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="false">
          Home
        </button>
        <div class="collapse" id="home-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Overview</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Updates</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Reports</a></li>
          </ul>
        </div>
      </li>
      <li class="mb-1">
        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed"
          data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="false">
          Dashboard
        </button>
        <div class="collapse" id="dashboard-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Overview</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Weekly</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Monthly</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Annually</a></li>
          </ul>
        </div>
      </li>
      <li class="mb-1">
        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed"
          data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
          Orders
        </button>
        <div class="collapse" id="orders-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">New</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Processed</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Shipped</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Returned</a></li>
          </ul>
        </div>
      </li>
      <li class="border-top my-3"></li>
      <li class="mb-1">
        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed"
          data-bs-toggle="collapse" data-bs-target="#account-collapse" aria-expanded="false">
          Account
        </button>
        <div class="collapse" id="account-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">New...</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Profile</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Settings</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Sign out</a></li>
          </ul>
        </div>
      </li>
    </ul>
    <hr>
    <div class="dropdown">
      <a href="#" class="d-flex align-items-center link-body-emphasis text-decoration-none dropdown-toggle"
        data-bs-toggle="dropdown" aria-expanded="true">
        <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
        <span class="d-print-block"><strong>mdo</strong></span>
      </a>
      <ul class="dropdown-menu text-small shadow">
        <li><a class="dropdown-item" href="#">New project...</a></li>
        <li><a class="dropdown-item" href="#">Settings</a></li>
        <li><a class="dropdown-item" href="#">Profile</a></li>
        <li>
          <hr class="dropdown-divider">
        </li>
        <li><a class="dropdown-item" href="#">Sign out</a></li>
      </ul>
    </div>
  </aside>

  <main class="col-sm-10 bg-body-tertiary" id="main">
    <div class="container-fluid">

      <div
        class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
        id="title">
        <h1 class="h2">Dashboard</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
          <div class="btn-group me-2">
            <button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
            <button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
          </div>
          <button type="button"
            class="btn btn-sm btn-outline-secondary dropdown-toggle d-flex align-items-center gap-1">
            <i class="bi bi-calendar3"></i>
            This week
          </button>
        </div>
      </div>

      <div class="row">
      </div>

      <!-- Nueva grafica -->
      <div class="row">
        <nav aria-label="breadcrumb">
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">Overview</li>
          </ol>
        </nav>
        <p>This is the homepage of a simple admin interface which is part of a tutorial written on Themesberg</p>
        <div class="row my-4">
          <div class="col-12 col-md-6 col-lg-3 mb-4 mb-lg-0">
            <div class="card">
              <h5 class="card-header">Customers</h5>
              <div class="card-body">
                <h5 class="card-title">345k</h5>
                <p class="card-text">Feb 1 - Apr 1, United States</p>
                <p class="card-text text-success">18.2% increase since last month</p>
              </div>
            </div>
          </div>
          <div class="col-12 col-md-6 mb-4 mb-lg-0 col-lg-3">
            <div class="card">
              <h5 class="card-header">Revenue</h5>
              <div class="card-body">
                <h5 class="card-title">$2.4k</h5>
                <p class="card-text">Feb 1 - Apr 1, United States</p>
                <p class="card-text text-success">4.6% increase since last month</p>
              </div>
            </div>
          </div>
          <div class="col-12 col-md-6 mb-4 mb-lg-0 col-lg-3">
            <div class="card">
              <h5 class="card-header">Purchases</h5>
              <div class="card-body">
                <h5 class="card-title">43</h5>
                <p class="card-text">Feb 1 - Apr 1, United States</p>
                <p class="card-text text-danger">2.6% decrease since last month</p>
              </div>
            </div>
          </div>
          <div class="col-12 col-md-6 mb-4 mb-lg-0 col-lg-3">
            <div class="card">
              <h5 class="card-header">Traffic</h5>
              <div class="card-body">
                <h5 class="card-title">64k</h5>
                <p class="card-text">Feb 1 - Apr 1, United States</p>
                <p class="card-text text-success">2.5% increase since last month</p>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-12 col-xl-8 mb-4 mb-lg-0">
            <div class="card">
              <h5 class="card-header">Latest transactions</h5>
              <div class="card-body">
                <div class="table-responsive">
                  <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">Order</th>
                        <th scope="col">Product</th>
                        <th scope="col">Customer</th>
                        <th scope="col">Total</th>
                        <th scope="col">Date</th>
                        <th scope="col"></th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope="row">17371705</th>
                        <td>Volt Premium Bootstrap 5 Dashboard</td>
                        <td>johndoe@gmail.com</td>
                        <td>?��61.11</td>
                        <td>Aug 31 2020</td>
                        <td><a href="#" class="btn btn-sm btn-primary">View</a></td>
                      </tr>
                      <tr>
                        <th scope="row">17370540</th>
                        <td>Pixel Pro Premium Bootstrap UI Kit</td>
                        <td>jacob.monroe@company.com</td>
                        <td>$153.11</td>
                        <td>Aug 28 2020</td>
                        <td><a href="#" class="btn btn-sm btn-primary">View</a></td>
                      </tr>
                      <tr>
                        <th scope="row">17371705</th>
                        <td>Volt Premium Bootstrap 5 Dashboard</td>
                        <td>johndoe@gmail.com</td>
                        <td>?��61.11</td>
                        <td>Aug 31 2020</td>
                        <td><a href="#" class="btn btn-sm btn-primary">View</a></td>
                      </tr>
                      <tr>
                        <th scope="row">17370540</th>
                        <td>Pixel Pro Premium Bootstrap UI Kit</td>
                        <td>jacob.monroe@company.com</td>
                        <td>$153.11</td>
                        <td>Aug 28 2020</td>
                        <td><a href="#" class="btn btn-sm btn-primary">View</a></td>
                      </tr>
                      <tr>
                        <th scope="row">17371705</th>
                        <td>Volt Premium Bootstrap 5 Dashboard</td>
                        <td>johndoe@gmail.com</td>
                        <td>?��61.11</td>
                        <td>Aug 31 2020</td>
                        <td><a href="#" class="btn btn-sm btn-primary">View</a></td>
                      </tr>
                      <tr>
                        <th scope="row">17370540</th>
                        <td>Pixel Pro Premium Bootstrap UI Kit</td>
                        <td>jacob.monroe@company.com</td>
                        <td>$153.11</td>
                        <td>Aug 28 2020</td>
                        <td><a href="#" class="btn btn-sm btn-primary">View</a></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <a href="#" class="btn btn-block btn-light">View all</a>
              </div>
            </div>
          </div>
          <div class="col-12 col-xl-4">
            <div class="card">
              <h5 class="card-header">Traffic last 6 months</h5>
              <div class="card-body">
                <div id="traffic-chart"></div>
              </div>
            </div>
          </div>
        </div>


      </div>

      <div class="row">
        <canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>

        <h2>Section title</h2>
        <div class="table-responsive small">
          <table class="table table-striped table-sm">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Header</th>
                <th scope="col">Header</th>
                <th scope="col">Header</th>
                <th scope="col">Header</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1,001</td>
                <td>random</td>
                <td>data</td>
                <td>placeholder</td>
                <td>text</td>
              </tr>
              <tr>
                <td>1,002</td>
                <td>placeholder</td>
                <td>irrelevant</td>
                <td>visual</td>
                <td>layout</td>
              </tr>
              <tr>
                <td>1,003</td>
                <td>data</td>
                <td>rich</td>
                <td>dashboard</td>
                <td>tabular</td>
              </tr>
              <tr>
                <td>1,003</td>
                <td>information</td>
                <td>placeholder</td>
                <td>illustrative</td>
                <td>data</td>
              </tr>
              <tr>
                <td>1,004</td>
                <td>text</td>
                <td>random</td>
                <td>layout</td>
                <td>dashboard</td>
              </tr>
              <tr>
                <td>1,005</td>
                <td>dashboard</td>
                <td>irrelevant</td>
                <td>text</td>
                <td>placeholder</td>
              </tr>
              <tr>
                <td>1,006</td>
                <td>dashboard</td>
                <td>illustrative</td>
                <td>rich</td>
                <td>data</td>
              </tr>
              <tr>
                <td>1,007</td>
                <td>placeholder</td>
                <td>tabular</td>
                <td>information</td>
                <td>irrelevant</td>
              </tr>
              <tr>
                <td>1,008</td>
                <td>random</td>
                <td>data</td>
                <td>placeholder</td>
                <td>text</td>
              </tr>
              <tr>
                <td>1,009</td>
                <td>placeholder</td>
                <td>irrelevant</td>
                <td>visual</td>
                <td>layout</td>
              </tr>
              <tr>
                <td>1,010</td>
                <td>data</td>
                <td>rich</td>
                <td>dashboard</td>
                <td>tabular</td>
              </tr>
              <tr>
                <td>1,011</td>
                <td>information</td>
                <td>placeholder</td>
                <td>illustrative</td>
                <td>data</td>
              </tr>
              <tr>
                <td>1,012</td>
                <td>text</td>
                <td>placeholder</td>
                <td>layout</td>
                <td>dashboard</td>
              </tr>
              <tr>
                <td>1,013</td>
                <td>dashboard</td>
                <td>irrelevant</td>
                <td>text</td>
                <td>visual</td>
              </tr>
              <tr>
                <td>1,014</td>
                <td>dashboard</td>
                <td>illustrative</td>
                <td>rich</td>
                <td>data</td>
              </tr>
              <tr>
                <td>1,015</td>
                <td>random</td>
                <td>tabular</td>
                <td>information</td>
                <td>text</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <div class="my-3 p-3 bg-body rounded shadow-sm">
        <h6 class="border-bottom pb-2 mb-0">Recent updates</h6>
        <div class="d-flex text-body-secondary pt-3">
          <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 32x32" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#007bff"/><text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text></svg>
          <p class="pb-3 mb-0 small lh-sm border-bottom">
            <strong class="d-block text-gray-dark">@username</strong>
            Some representative placeholder content, with some information about this user. Imagine this being some sort of status update, perhaps?
          </p>
        </div>
        <div class="d-flex text-body-secondary pt-3">
          <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 32x32" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#e83e8c"/><text x="50%" y="50%" fill="#e83e8c" dy=".3em">32x32</text></svg>
          <p class="pb-3 mb-0 small lh-sm border-bottom">
            <strong class="d-block text-gray-dark">@username</strong>
            Some more representative placeholder content, related to this other user. Another status update, perhaps.
          </p>
        </div>
        <div class="d-flex text-body-secondary pt-3">
          <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 32x32" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#6f42c1"/><text x="50%" y="50%" fill="#6f42c1" dy=".3em">32x32</text></svg>
          <p class="pb-3 mb-0 small lh-sm border-bottom">
            <strong class="d-block text-gray-dark">@username</strong>
            This user also gets some representative placeholder content. Maybe they did something interesting, and you really want to highlight this in the recent updates.
          </p>
        </div>
        <small class="d-block text-end mt-3">
          <a href="#">All updates</a>
        </small>
      </div>

      <div class="my-3 p-3 bg-body rounded shadow-sm">
        <h6 class="border-bottom pb-2 mb-0">Suggestions</h6>
        <div class="d-flex text-body-secondary pt-3">
          <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 32x32" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#007bff"/><text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text></svg>
          <div class="pb-3 mb-0 small lh-sm border-bottom w-100">
            <div class="d-flex justify-content-between">
              <strong class="text-gray-dark">Full Name</strong>
              <a href="#">Follow</a>
            </div>
            <span class="d-block">@username</span>
          </div>
        </div>
        <div class="d-flex text-body-secondary pt-3">
          <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 32x32" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#007bff"/><text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text></svg>
          <div class="pb-3 mb-0 small lh-sm border-bottom w-100">
            <div class="d-flex justify-content-between">
              <strong class="text-gray-dark">Full Name</strong>
              <a href="#">Follow</a>
            </div>
            <span class="d-block">@username</span>
          </div>
        </div>
        <div class="d-flex text-body-secondary pt-3">
          <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 32x32" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#007bff"/><text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text></svg>
          <div class="pb-3 mb-0 small lh-sm border-bottom w-100">
            <div class="d-flex justify-content-between">
              <strong class="text-gray-dark">Full Name</strong>
              <a href="#">Follow</a>
            </div>
            <span class="d-block">@username</span>
          </div>
        </div>
        <small class="d-block text-end mt-3">
          <a href="#">All suggestions</a>
        </small>
      </div>

      <div class="row">
        <footer class="pt-5 d-flex justify-content-between">
          <span>Copyright © 2019-2020 <a href="https://themesberg.com">Themesberg</a></span>
          <ul class="nav m-0">
            <li class="nav-item">
              <a class="nav-link text-secondary" aria-current="page" href="#">Privacy Policy</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-secondary" href="#">Terms and conditions</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-secondary" href="#">Contact</a>
            </li>
          </ul>
        </footer>
      </div>

    </div>
  </main>

  <div class="offcanvas offcanvas-start" data-bs-scroll="true" tabindex="-1" id="offcanvasExample"
    aria-labelledby="offcanvasExampleLabel">
    <div class="offcanvas-header">
      <h5 class="offcanvas-title" id="offcanvasExampleLabel">Offcanvas</h5>
      <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
      <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
        <svg class="bi pe-none me-2" width="40" height="32">
          <use xlink:href="#bootstrap" />
        </svg>
        <span class="d-print-block">Sidebar</span>
      </a>
      <hr>
      <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
          <a href="#" class="nav-link link-body-emphasis" aria-current="page">
            <p class="bi bi-house-door" style="position:fixed;"> Home</p><br>
          </a>
        </li>
        <li>
          <a href="#" class="nav-link link-body-emphasis">
            <p class="bi bi-speedometer2" style="position:fixed;"> Dashboard</p><br>
          </a>
        </li>
        <li>
          <a href="#" class="nav-link link-body-emphasis">
            <p class="bi bi-table" style="position:fixed;"> Orders</p><br>
          </a>
        </li>
        <li>
          <a href="#" class="nav-link link-body-emphasis">
            <p class="bi bi-grid" style="position:fixed;"> Products</p><br>
          </a>
        </li>
        <li>
          <a href="#" class="nav-link link-body-emphasis">
            <p class="bi bi-person-circle" style="position:fixed;"> Customers</p><br>
          </a>
        </li>
      </ul>
      <hr>
      <ul class="list-unstyled ps-0">
        <li class="mb-1">
          <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed"
            data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="false">
            Home
          </button>
          <div class="collapse" id="home-collapse">
            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
              <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Overview</a></li>
              <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Updates</a></li>
              <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Reports</a></li>
            </ul>
          </div>
        </li>
        <li class="mb-1">
          <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed"
            data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="false">
            Dashboard
          </button>
          <div class="collapse" id="dashboard-collapse">
            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
              <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Overview</a></li>
              <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Weekly</a></li>
              <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Monthly</a></li>
              <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Annually</a></li>
            </ul>
          </div>
        </li>
        <li class="mb-1">
          <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed"
            data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
            Orders
          </button>
          <div class="collapse" id="orders-collapse">
            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
              <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">New</a></li>
              <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Processed</a></li>
              <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Shipped</a></li>
              <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Returned</a></li>
            </ul>
          </div>
        </li>
        <li class="border-top my-3"></li>
        <li class="mb-1">
          <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed"
            data-bs-toggle="collapse" data-bs-target="#account-collapse" aria-expanded="false">
            Account
          </button>
          <div class="collapse" id="account-collapse">
            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
              <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">New...</a></li>
              <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Profile</a></li>
              <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Settings</a></li>
              <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Sign out</a></li>
            </ul>
          </div>
        </li>
      </ul>
      <hr>
      <div class="dropdown">
        <a href="#" class="d-flex align-items-center link-body-emphasis text-decoration-none dropdown-toggle"
          data-bs-toggle="dropdown" aria-expanded="true">
          <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
          <span class="d-print-block"><strong>mdo</strong></span>
        </a>
        <ul class="dropdown-menu text-small shadow">
          <li><a class="dropdown-item" href="#">New project...</a></li>
          <li><a class="dropdown-item" href="#">Settings</a></li>
          <li><a class="dropdown-item" href="#">Profile</a></li>
          <li>
            <hr class="dropdown-divider">
          </li>
          <li><a class="dropdown-item" href="#">Sign out</a></li>
        </ul>
      </div>
    </div>
  </div>

  <!-- Script de bootstap 5.2.3 -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    crossorigin="anonymous"></script>
  <!-- Jquery -->
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"
    integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>


  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
    integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
    crossorigin="anonymous"></script>
  
    <!-- ChartsJS -->
  <script src="https://cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/chart.js@4.3.2/dist/chart.umd.js"
    integrity="sha384-eI7PSr3L1XLISH8JdDII5YN/njoSsxfbrkCTnJrzXt+ENP5MOVBxD+l6sEG4zoLp"
    crossorigin="anonymous"></script>
  <!-- Github buttons -->
  <script async defer src="https://buttons.github.io/buttons.js"></script>
  <script src="resources/js/dashboard.js"></script>
  <script>
    new Chartist.Line('#traffic-chart', {
      labels: ['January', 'Februrary', 'March', 'April', 'May', 'June'],
      series: [
        [23000, 25000, 19000, 34000, 56000, 64000]
      ]
    }, {
      low: 0,
      showArea: true
    });
  </script>

  <script>
    function changeclass() {
      $("#main").toggleClass('col-sm-10 col-sm-12');
    }

  </script>
</body>

</html>