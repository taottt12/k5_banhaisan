<?php
include 'connect.php';

$query = "SELECT * FROM `tbl_mathang` ORDER BY ma_mh DESC";
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
