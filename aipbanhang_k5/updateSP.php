<?php
include 'connect.php';

// $id_sanpham = "20";
// $tensanpham = "123jhdfh";
// $dongia = "2000";
// $donvitinh = "Ký";
// $hinhanh = "dfghkzsdgj5ih27tfgreuiwy45";
// $mancc = "2";
// $malh = "2";

$id_sanpham = $_POST['idMH'];
$tensanpham = $_POST['tensanpham'];
$dongia = $_POST['dongia'];
$donvitinh = $_POST['donvitinh'];
$hinhanh = $_POST['hinhanh'];
$mancc = $_POST['mancc'];
$malh = $_POST['malh'];

if (
    $id_sanpham != null && $tensanpham != null && $dongia != null
    && $donvitinh != null && $hinhanh != "aa" && $mancc != null && $malh != null
) {

    $imagePath =  date("d-m-y") . '-' . time() . rand(1000, 100000) . '.jpg';
    if (file_put_contents('./image/' . $imagePath, base64_decode($hinhanh))) {
        $sqlInsert = "UPDATE `tbl_mathang` SET `tensanpham`='$tensanpham',`dongia`=$dongia,
        `donvitinh`='$donvitinh',`hinhanh`='$imagePath',`mancc`='$mancc',`malh`='$malh' WHERE `ma_mh`='$id_sanpham'";

        if (mysqli_query($con, $sqlInsert)) {
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

    $sqlInsert = "UPDATE `tbl_mathang` SET `tensanpham`='$tensanpham',`dongia`=$dongia,
        `donvitinh`='$donvitinh',`mancc`='$mancc',`malh`='$malh' WHERE `ma_mh`='$id_sanpham'";

    if (mysqli_query($con, $sqlInsert)) {
        $response["success"] = 1;
        echo json_encode($response);
    } else {
        $response["success"] = 2;
        echo json_encode($response);
    }

    // $response["success"] = 1;
    // echo json_encode($response);
}
