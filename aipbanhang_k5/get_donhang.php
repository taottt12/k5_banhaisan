<?php
include 'connect.php';

$query = "SELECT kh.tenKH, kh.sdtKH, kh.diaChiKH, hbd.ngayban FROM tbl_hoadonban hbd, tbl_khachhang kh WHERE hbd.id_KH = kh.id_KH ";
$result = mysqli_query($con, $query);

if (mysqli_num_rows($result) > 0) {
    while ($row = mysqli_fetch_assoc($result)) {
        $responsejson[] = ($row);
    }
    $arr = [
        'success' => 0,
        'message' => "Thanh Cong",
        'result' => $responsejson
    ];
} else {
    $arr = [
        'success' => 1,
        'message' => "Khong Thanh Cong",
        'result' => $responsejson
    ];
}


print_r(json_encode($arr));
mysqli_close($con);
