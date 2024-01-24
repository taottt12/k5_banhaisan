<?php
include 'connect.php';

// $data = array(
//     'image' => 'base64_encoded_image_data.png',
//     'tenSP' => 'Product Name',
//     'donGia' => '100',
//     'donViTinh' => 'Unit',
//     'maNCC' => '2',
//     'maLH' => '1'
// );
// $json = json_encode($data);

$response = array();

if (
    !empty($_POST['image']) && !empty($_POST['tenSP'])  && !empty($_POST['donGia'])
    && !empty($_POST['donViTinh'])  && !empty($_POST['maNCC'])  && !empty($_POST['maLH'])
) {
    $base64Image = $_POST['image'];
    $tenSP = $_POST['tenSP'];
    $donGia = $_POST['donGia'];
    $donViTinh = $_POST['donViTinh'];
    $maNCC = $_POST['maNCC'];
    $maLH = $_POST['maLH'];

    var_dump($base64Image);
    var_dump($tenSP);
    var_dump($donGia);
    var_dump($donViTinh);
    var_dump($maNCC);
    var_dump($maLH);

    $imagePath =  date("d-m-y") . '-' . time() . rand(1000, 100000) . '.jpg';

    if (file_put_contents('./image/' . $imagePath, base64_decode($base64Image))) {
        $sqlInsert = "INSERT INTO `tbl_mathang`( `tensanpham`, `soluong`, `dongia`, `donvitinh`, `ghichu`, `hinhanh`, `mancc`, `malh`) 
        VALUES ('$tenSP','0','$donGia','$donViTinh',null,'" .  $imagePath . "','$maNCC','$maLH')";

        // $sqlInsert = "INSERT INTO `tbl_mathang`( `hinhanh`) 
        // VALUES ('" .  $imagePath . "')";
        if (mysqli_query($con, $sqlInsert)) {
            $response["status"] = "success";
            $response["message"] = "Inserted";
        } else {
            $response["status"] = "error";
            $response["message"] = "Failed to insert";
        }
    } else {
        $response["status"] = "No";
        $response["message"] = "Failed to upload image";
    }
} else {
    $response["status"] = "errorNo";
    $response["message"] = "No image found";
}

echo json_encode($response);
