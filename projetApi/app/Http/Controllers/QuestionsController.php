<?php

namespace App\Http\Controllers;
use App\Models\Question;
use App\Models\User;
use App\Models\Reply;
use Illuminate\Http\Request;
use Illuminate\Support\Arr;

class QuestionsController extends Controller
{
    public function index(){
        $data = Question::all();
        $resultArray = array();
        $tempArray = array();
        $stylesArray = array();
        // Loop through each result
       foreach($data as $row)
         {
            $id = $row->idUser;
            $user = User::where('id', '=', $id)->first();
            $fn = $user->firstname;
            $ln = $user->lastname;
            $name = $fn . " " .$ln;
        $tempArray = $row;
        $tempArray =  Arr::add($tempArray, "username" , $name);
        array_push($resultArray, $tempArray);
        
         }
        // Encode the array to JSON and output the results
        echo json_encode($resultArray);
    }
    public function addnew(Request $request){
        //dd($request);
        $question = new Question;
        $question->message = $request->input('message');
        $question->idUser = $request->input('idUser');
        $question->save();
        return response()->json("Added Successfuly!");
    }
    public function questreplies($pos){
        $pos=$pos+1;
        $data = Question::all();
        $size = $data->count();
           for ($i = 0; $i < $pos; $i++) {
            $need = $data[$i];
        }
        $id = $need->id;
        $data = Reply::where('idQuest', $id)->get();
         // echo json_encode($id);
         $resultArray = array();
         $tempArray = array();
         $stylesArray = array();
         // Loop through each result
         foreach($data as $row)
         {
            $id = $row->idUser;
            $user = User::where('id', '=', $id)->first();
            $fn = $user->firstname;
            $ln = $user->lastname;
            $name = $fn . " " .$ln;
        $tempArray = $row;
        $tempArray =  Arr::add($tempArray, "username" , $name);
        array_push($resultArray, $tempArray);
        
         }
         // Encode the array to JSON and output the results
         echo json_encode($resultArray);  
      
    }
}
