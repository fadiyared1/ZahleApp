<?php

namespace App\Http\Controllers;
use App\Models\Nightlife;
use Illuminate\Http\Request;

class NightsController extends Controller
{
    public function index(){
        $data = Nightlife::all();
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
        $data = Nightlife::all();
        $size = $data->count();
           for ($i = 0; $i < $pos; $i++) {
            $need = $data[$i];
        }
          echo json_encode($need);
      }
}
