<?php
    require_once 'tuyen.php';
    
    $tuyenxeObject = new TuyenXe(1,1);
        $json_array = $tuyenxeObject->XuatTuyenXe();
        echo json_encode($json_array);
?>