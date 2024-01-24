<?php
include 'connect.php';

// $idUser = "6";

$idUser = $_POST['idUS'];
if ($idUser != null) {

    $sql = "SELECT * FROM tbl_account WHERE id = $idUser";
    $result = mysqli_query($con, $sql);
    if (mysqli_num_rows($result) > 0) {
        $row = mysqli_fetch_assoc($result);

        $response["success"] = 1;
        $response["name"] = $row["name"];
        $response["sdt"] = $row["sdt"];
        $response["email"] = $row["email"];

        echo json_encode($response);
    } else {
        $response["success"] = 2;
        echo json_encode($response);
    }
} else {
    $response["success"] = 3;
    echo json_encode($response);
}
