<?php
include 'connect.php';


// $pass = "321";
// $passnew = "123";
// $idUser = 6;

$pass = $_POST['pass'];
$passnew = $_POST['passnew'];
$idUser = $_POST['idUs'];


if ($pass != null && $passnew != null && $idUser != null) {
    $sql_check = "SELECT `password` From tbl_account WHERE `id` = $idUser";

    $Rs = mysqli_query($con, $sql_check);
    $pass_check = mysqli_fetch_array($Rs)['password'];

    if ($pass == $pass_check) {
        $query = "UPDATE `tbl_account` SET `password`='$passnew' WHERE `id`= '$idUser'";

        if (mysqli_query($con, $query)) {

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
} else {
    $response["success"] = 0;
    $response["message"] = "Dang nhap that bai";
    echo json_encode($response);
}


mysqli_close($con);
