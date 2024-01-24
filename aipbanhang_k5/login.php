<?php
include 'connect.php';

$username = $_POST['username'];
$password = $_POST['password'];

$query = "SELECT * FROM tbl_account WHERE username='$username' AND password='$password'";
$result = mysqli_query($con, $query);


if (mysqli_num_rows($result) > 0) {
    $row = mysqli_fetch_assoc($result);

    $response["success"] = 1;
    $response["id"] = $row["id"];
    $response["name"] = $row["name"];
    $response["sdt"] = $row["sdt"];
    $response["email"] = $row["email"];
    $response["authorities"] = $row["authorities"];

    echo json_encode($response);
} else {
    $response["success"] = 0;
    echo json_encode($response);
}
mysqli_close($con);
