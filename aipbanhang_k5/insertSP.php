<?php
include 'connect.php';

// $tensanpham = "123jhdfh";
// $dongia = "2000";
// $donvitinh = "Ký";
// $hinhanh = "dfghkzsdgj5ih27tfgreuiwy45";
// $mancc = "2";
// $malh = "2";

$tensanpham = $_POST['tensanpham'];
$dongia = $_POST['dongia'];
$donvitinh = $_POST['donvitinh'];
$hinhanh = $_POST['hinhanh'];
$mancc = $_POST['mancc'];
$malh = $_POST['malh'];

$imagePath =  date("d-m-y") . '-' . time() . rand(1000, 100000) . '.jpg';
if (file_put_contents('./image/' . $imagePath, base64_decode($hinhanh))) {
    $sqlInsert = "INSERT INTO `tbl_mathang`( `tensanpham`, `soluong`, `dongia`, `donvitinh`, `ghichu`, `hinhanh`, `mancc`, `malh`) 
        VALUES ('$tensanpham','0','$dongia','$donvitinh',null,'" .  $imagePath . "','$mancc','$malh')";

    // $sqlInsert = "INSERT INTO `tbl_mathang`( `hinhanh`) 
    // VALUES ('" .  $imagePath . "')";
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
