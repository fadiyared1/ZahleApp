<?php

namespace App\Http\Controllers;
use App\Models\Restaurant;
use Illuminate\Http\Request;

class RestaurantsController extends Controller
{
    public function index(){
        $data = Restaurant::all();
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
      $data = Restaurant::all();
      $size = $data->count();
         for ($i = 0; $i < $pos; $i++) {
          $need = $data[$i];
      }
        echo json_encode($need);
    }
}
