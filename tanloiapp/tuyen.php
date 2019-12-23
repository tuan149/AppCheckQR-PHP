<?php
include_once 'db-connect.php';
    //where NgayKhoiHanh = '$ngaykh' AND GioKhoiHanh = '$giokh '
    
    class TuyenXe{
        private $db;
        
       
        //private $db_table = "tuyen";       

        function TuyenXe($id, $TenTuyen){
            $this->ID=$id;
            $this->TenTuyen=$TenTuyen;
        }
       
        public function XuatTuyenXe(){
                
            $this->db = new DbConnect();
            
            $json = array();
            
                $query = "select * from tuyen";
                $result = mysqli_query($this->db->getDb(), $query);
                while($row=mysqli_fetch_assoc($result)){

                    array_push($json,new TuyenXe($row['id'],$row['TenTuyen']));
                }
            
            return $json;
            
        }
    }
    ?>