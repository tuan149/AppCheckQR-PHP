<?php
include_once 'db-connect.php';
    //where NgayKhoiHanh = '$ngaykh' AND GioKhoiHanh = '$giokh '
    
    class KhachHang{
        private $db;
        
       
        //private $db_table = "tuyen";  


        function KhachHang($HoTen, $SDT,$Email, $TrangThai,$TenGhe){
            $this->HoTen=$HoTen;
            $this->SDT=$SDT;
            $this->Email=$Email;
            $this->TrangThai = $TrangThai;
            $this->TenGhe=$TenGhe;
        }

        public function DanhSachKhachHang($NgayKhoiHanh,$GioKhoiHanh,$idTuyen){
                
            $this->db = new DbConnect();
            
            $json = array();
            
                $query = "SELECT DISTINCT t1.*, t2.TrangThai, t5.TenGhe FROM khachhang t1, ve t2, tuyen t3, lichchay t4,chitietghe t5, chitietve t6 WHERE t1.id=t2.idKH AND t2.NgayKhoiHanh= '$NgayKhoiHanh'  AND t2.GioKhoiHanh= '$GioKhoiHanh' AND t2.idLC=t4.id AND t4.idTuyen=t3.id AND  t3.id='$idTuyen' AND t5.id=t6.idGhe";
                $result = mysqli_query($this->db->getDb(), $query);
                while($row=mysqli_fetch_assoc($result)){
                    array_push($json,new KhachHang($row['HoTen'],$row['SDT'],$row['Email'], $row['TrangThai'],$row['TenGhe']));
                }
            
            return $json;
            
        }
    }
    ?>