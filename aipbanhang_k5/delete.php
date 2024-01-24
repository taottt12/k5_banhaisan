<?php
include 'connect.php';

$MaMH = $_POST['ma_mh'];
$query = "DELETE FROM `tbl_mathang` WHERE ma_mh = '$MaMH'";

if (mysqli_query($con, $query)) {
    $response["success"] = 1;
    echo json_encode($response);
}
