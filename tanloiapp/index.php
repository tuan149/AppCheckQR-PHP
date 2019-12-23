<?php
    require_once 'user.php';
    
    $username = "";
    
    $password = "dsa";
    
    if(isset($_POST['username'])){
        $username = $_POST['username'];
    }
    
    if(isset($_POST['password'])){
        $password = $_POST['password'];
    }
    
    $userObject = new User();
    
    if(!empty($username) && !empty($password)){
        
        $hashed_password = md5($password);
        
        $json_array = $userObject->loginUsers($username, $hashed_password);
        
        echo json_encode($json_array);
    }
    else {
        echo '{"success":0,"message":"Vui lòng nhập đủ thông tin"}';
    }

?>