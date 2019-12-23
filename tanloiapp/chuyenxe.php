<?php
include_once 'db-connect.php';
    //where NgayKhoiHanh = '$ngaykh' AND GioKhoiHanh = '$giokh '
    class ChuyenXe{
        
        private $db;
        
        private $db_table = "ve";
        
        public function __construct(){
            $this->db = new DbConnect();
        }
        
        public function isChuyenXeExist($ngaykh,$giokh){
            
            $query = "select * from ve where NgayKhoiHanh = '$ngaykh' and GioKhoiHanh='$giokh'";
            
            $result = mysqli_query($this->db->getDb(), $query);
            
            if(mysqli_num_rows($result) > 0){
                mysqli_close($this->db->getDb());
                return true;
            }
            
            mysqli_close($this->db->getDb());
            
            return false;
        }
        
        public function XuatChuyenXe($ngaykh,$giokh){
           
            $json = array();
            $canXuatChuyenXe = $this->isChuyenXeExist($ngaykh,$giokh);
            if($canXuatChuyenXe){
                // while($row=mysqli_fletch_assoc($result)){
                //     $json['success'] =$row['id'];
                // }
                $json['success'] = 1;
                $json['message'] = "Có chuyến xe";
            }else{
                $json['success'] = 0;
                $json['message'] = "Không tìm thấy";
            }
            return $json;
        }
    }
    ?>