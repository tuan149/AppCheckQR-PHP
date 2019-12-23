<?php
    require_once 'chuyenxe.php';
    
    $ngaykh ="";
    
    $giokh ="";

    $lichchay="";
    
    if(isset($_POST['ngaykh'])){
        $ngaykh = $_POST['ngaykh'];
    }
    
    if(isset($_POST['giokh'])){
        $giokh = $_POST['giokh'];
    }

    if(isset($_POST['lichchay'])){
        $giokh = $_POST['lichchay'];
    }
    
    $chuyenxeObject = new ChuyenXe();
    
    if(!empty($ngaykh)&& !empty($giokh)){
        
        $json_array = $chuyenxeObject->XuatChuyenXe($ngaykh,$giokh);
        
        echo json_encode($json_array);
    }
    else {
        echo '{"success":0,"message":"Vui lòng nhập đủ thông tin"}';
    }
?>