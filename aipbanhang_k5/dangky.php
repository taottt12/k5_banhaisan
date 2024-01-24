<?php
include 'connect.php';

$username = $_POST['username'];
$password = $_POST['password'];
$name = $_POST['name'];
$sdt = $_POST['sdt'];
$email = $_POST['email'];

$sql_DK = "INSERT INTO `tbl_account`(`username`, `password`, `name`, `email`, `sdt`, `authorities`) 
VALUES ('$username','$password','$name','$email','$sdt','1')";

if (mysqli_query($con, $sql_DK) === TRUE) {
    $response["success"] = 1;
    echo json_encode($response);
} else {
    $response["success"] = 0;
    echo json_encode($response);
}
