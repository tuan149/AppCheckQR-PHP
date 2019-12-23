<?php
    require_once 'vexe.php';
    
    $NgayKhoiHanh = "02-10-2019";
    
    $GioKhoiHanh = "08:00";

    $idTuyen="5";
    
    $MaBiMat="gVdnP617eSihyZ4v0c3iSA7M7ttQbLu1";

    if(isset($_POST['NgayKhoiHanh'])){
        $NgayKhoiHanh = $_POST['NgayKhoiHanh'];
    }
    
    if(isset($_POST['GioKhoiHanh'])){
        $GioKhoiHanh = $_POST['GioKhoiHanh'];
    }
    
    if(isset($_POST['idTuyen'])){
        $idTuyen = $_POST['idTuyen'];
    }

    if(isset($_POST['MaBiMat'])){
        $MaBiMat = $_POST['MaBiMat'];
    }
    $vexeObject = new VeXe();
      
    $json_array = $vexeObject->DungVeXe($NgayKhoiHanh,$GioKhoiHanh,$idTuyen,$MaBiMat);
        
    echo json_encode($json_array);

?>