<?php
    require_once 'khachhang.php';
   
    $NgayKhoiHanh = "2019-10-02";
    
    $GioKhoiHanh = "8h00";

    $idTuyen="5";

    if(isset($_POST['NgayKhoiHanh'])){
        $NgayKhoiHanh = $_POST['NgayKhoiHanh'];
    }
    
    if(isset($_POST['GioKhoiHanh'])){
        $GioKhoiHanh = $_POST['GioKhoiHanh'];
    }
    
    if(isset($_POST['idTuyen'])){
        $idTuyen = $_POST['idTuyen'];
    }

   
        
    $KhachHangObject = new KhachHang(1,1,1,1,1);
        $json_array = $KhachHangObject->DanhSachKhachHang($NgayKhoiHanh,$GioKhoiHanh,$idTuyen);
        echo json_encode($json_array);
?>