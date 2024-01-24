<?php
include 'connect.php';

$MAMH = $_POST['MAMH'];
$SOLUONG = $_POST['SOLUONG'];
$MANCC = $_POST['MANCC'];

if ($MAMH != null && $SOLUONG != null && $MANCC != null) {

    $sql_checkSL = "SELECT soluong FROM `tbl_mathang` WHERE ma_mh = '$MAMH'";
    $SL_check = mysqli_query($con, $sql_checkSL);

    $SL_Kho = mysqli_fetch_array($SL_check)['soluong'];

    $SLnew = $SL_Kho + $SOLUONG;

    $sqlInsertSL = "UPDATE `tbl_mathang` SET `soluong`='$SLnew' WHERE `ma_mh`='$MAMH'";

    if (mysqli_query($con, $sqlInsertSL)) {
        $sqlInsertHDN = "INSERT INTO `tbl_hoadonnhap`( `ma_mh`, `so_luong`, `ma_ncc`, `ngay_nhap`) 
        VALUES ('$MAMH','$SOLUONG','$MANCC',NOW() )";
        if (mysqli_query($con, $sqlInsertHDN)) {
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
}
