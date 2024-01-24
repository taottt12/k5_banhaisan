<?php
include "connect.php";

// $id_User = $_POST['id_User'];
// $tenKH = $_POST['tenkh'];
// $emailKH = $_POST['emaikh'];
// $sdtKH = $_POST['sdtKH'];
// $diachi = $_POST['diachi'];

// $json = '{
//     "ngaydathang": "2023-09-27",
//     "khachhang": {
//       "id_user": "123",
//       "tenkh": "John Doe",
//       "emailkh": "johndoe@example.com",
//       "sdtkh": "123456789",
//       "diachikh": "123 Main Street"
//     },
//     "giohang": [
//       {
//         "id_sanpham": "1",
//         "soluong": "2",
//         "gia": "10"
//       },
//       {
//         "id_sanpham": "2",
//         "soluong": "1",
//         "gia": "15"
//       }
//     ]
//   }';
// //json test
// $data = $json;

$data = $_POST['json'];

// Giải mã dữ liệu JSON nhận được
$donhang = json_decode($data, true);

// Lấy thông tin từ đơn hàng
$ngaydathang = $donhang['ngaydathang'];
$khachhang = $donhang['khachhang'];
$giohang = $donhang['giohang'];

// Lấy thông tin khách hàng
$id_user = $khachhang['id_user'];
$tenkh = $khachhang['tenkh'];
$emailkh = $khachhang['emailkh'];
$sdtkh = $khachhang['sdtkh'];
$diachikh = $khachhang['diachikh'];

///insert
$sql_KH = "INSERT INTO `tbl_khachhang`( `id_user`, `tenKH`, `emailKH`, `sdtKH`, `diaChiKH`) 
VALUES ('$id_user','$tenkh','$emailkh','$sdtkh','$diachikh')";

if (mysqli_query($con, $sql_KH) === TRUE) {
    $sql_idkh = "SELECT id_KH FROM `tbl_khachhang` ORDER BY id_KH DESC LIMIT 1";

    $result_idkh = mysqli_query($con, $sql_idkh);
    if (mysqli_num_rows($result_idkh) > 0) {
        $row_id = mysqli_fetch_assoc($result_idkh);
        $id_kh = $row_id['id_KH'];
    } else {
        echo "Error";
        $response["success"] = 0;
        echo json_encode($response);
    }

    $sql_hoadon = "INSERT INTO `tbl_hoadonban`( `id_KH`, `ngayban`, `ghichu`) 
    VALUES ('$id_kh','$ngaydathang','null')";

    if (mysqli_query($con, $sql_hoadon)) {
        $sql_idhdb = "SELECT id_HDB FROM `tbl_hoadonban` ORDER BY id_HDB DESC LIMIT 1;";
        $row_idhdb = mysqli_query($con, $sql_idhdb);
        $id_hdb = mysqli_fetch_array($row_idhdb)['id_HDB'];

        foreach ($giohang as $sanpham) {
            $id_sanpham = $sanpham['id_sanpham'];
            $soluong = intval($sanpham['soluong']);
            $gia = $sanpham['gia'];

            $sql_checkSL = "SELECT soluong FROM `tbl_mathang` WHERE ma_mh = '$id_sanpham'";
            $SL_check = mysqli_query($con, $sql_checkSL);

            $SL_Kho = mysqli_fetch_array($SL_check)['soluong'];
            // $sql_cthd = "INSERT INTO `tbl_cthdb`(`id_HDB`, `id_MH`, `giaban`, `soluong`, `mucgiamgia`) 
            // VALUES ('$id_hdb','$id_sanpham','$gia','$soluong','0')";
            // mysqli_query($con, $sql_cthd);

            if ($SL_Kho >= $soluong) {
                $sql_cthd = "INSERT INTO `tbl_cthdb`(`id_HDB`, `id_MH`, `giaban`, `soluong`, `mucgiamgia`) 
                VALUES ('$id_hdb','$id_sanpham','$gia','$soluong','0')";
                mysqli_query($con, $sql_cthd);

                $soluongConlai = $SL_Kho - $soluong;
                $sql_updateSLKho = "UPDATE `tbl_mathang` SET soluong='$soluongConlai' WHERE ma_mh='$id_sanpham'";
                mysqli_query($con, $sql_updateSLKho);
            } else {
                $sql_delHD = "DELETE FROM `tbl_hoadonban` WHERE id_HDB = '$id_hdb'";
                mysqli_query($con, $sql_delHD);
                $sql_delKH = "DELETE FROM `tbl_khachhang` WHERE id_KH = '$id_kh'";
            }
        }
        $response["success"] = 1;
        echo json_encode($response);
        // $sql_ktra = "SELECT * FROM `tbl_cthdb` WHERE id_HDB = $id_hdb";
        // $kq = mysqli_query($con, $sql_ktra);
        // if (mysqli_num_rows($kq) > 0) {
        //     $response["success"] = 1;
        //     echo json_encode($response);
        // }
    }
} else {
    $response["success"] = 0;
    echo json_encode($response);
}
