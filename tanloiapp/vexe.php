<?php
include_once 'db-connect.php';
    class VeXe{
        
        private $db;
        
        //private $db_table = "chitietve t1, ve t2, lichchay t3, tuyen t4";
        
        public function __construct(){
            $this->db = new DbConnect();
        }
        
        public function KTMaVe($NgayKhoiHanh,$GioKhoiHanh,$idTuyen,$MaBiMat){
            
            $query = "select * from chitietve t1, ve t2, lichchay t3, tuyen t4 where t1.MaBiMat='$MaBiMat'";
            
            $result = mysqli_query($this->db->getDb(), $query);
            
            if(mysqli_num_rows($result) > 0){

                return true;
            }
            
            return false;
        }

        public function KTLichChay($NgayKhoiHanh,$GioKhoiHanh,$idTuyen){
            
            $query = "select * from chitietve t1, ve t2, lichchay t3, tuyen t4 where t1.idVe = t2.id and t2.idLC = t3.id and t3.idTuyen = t4.id and t2.NgayKhoiHanh='$NgayKhoiHanh' and t2.GioKhoiHanh='$GioKhoiHanh' and t4.id='$idTuyen'";
            
            $result = mysqli_query($this->db->getDb(), $query);
            
            if(mysqli_num_rows($result) > 0){
                
                return true;
            }
            
            return false;
        }
        
        public function DungVeXe($NgayKhoiHanh,$GioKhoiHanh,$idTuyen,$MaBiMat){
           
            $json = array();
            $CoMaVe = $this->KTMaVe($NgayKhoiHanh,$GioKhoiHanh,$idTuyen,$MaBiMat);
            $CoLichChay = $this->KTLichChay($NgayKhoiHanh,$GioKhoiHanh,$idTuyen);
            
            if($CoMaVe){
                if($CoLichChay){
                    $query = "update ve set TrangThai=1 where id in (select idVe from chitietve where MaBiMat='$MaBiMat')";
                    mysqli_query($this->db->getDb(), $query);
                    $json['success'] = 1;
                    $json['message'] = "Vé hợp lệ";
                }
                else{
                    $json['success'] = 2;
                    $json['message'] = "Sai chuyến đi";
                }
                
            }else{
                $json['success'] = 0;
                $json['message'] = "Mã vé không hợp lệ";
            }
            return $json;
        }
    }
    ?>