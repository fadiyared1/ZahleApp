<?php

namespace App\Http\Controllers;
use App\Models\Traditionalhouse;
use Illuminate\Http\Request;

class HousesController extends Controller
{
    public function index(){
        $data = Traditionalhouse::all();
        $resultArray = array();
        $tempArray = array();
        $stylesArray = array();
        // Loop through each result
       foreach($data as $row)
         {
        $tempArray = $row;
        array_push($resultArray, $tempArray);
        
         }
        // Encode the array to JSON and output the results
        echo json_encode($resultArray);
    }
    public function selectone($pos){
        $pos=$pos+1;
        $data = Traditionalhouse::all();
        $size = $data->count();
           for ($i = 0; $i < $pos; $i++) {
            $need = $data[$i];
        }
          echo json_encode($need);
      }
}
