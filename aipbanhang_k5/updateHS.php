<?php
include 'connect.php';

// $ten = "alo";
// $sdt = "0984524";
// $email = "huagfgusvg";
// $idUs = "6";

$ten = $_POST['ten'];
$sdt = $_POST['sdt'];
$email = $_POST['email'];
$idUs = $_POST['idUser'];


if ($ten != null && $sdt != null && $email != null && $idUs != null) {

    $sqlupdateHS = "UPDATE `tbl_account` SET `name`='$ten',`email`='$email',`sdt`='$sdt' WHERE `id`=$idUs";
    if (mysqli_query($con, $sqlupdateHS)) {
        $response["success"] = 1;
        echo json_encode($response);
    } else {
        $response["success"] = 2;
        echo json_encode($response);
    }
} else {
    $response["success"] = 3;
    echo json_encode($response);
}
