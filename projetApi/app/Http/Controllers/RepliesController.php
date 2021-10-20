<?php

namespace App\Http\Controllers;
use App\Models\Reply;
use App\Models\User;
use App\Models\Question;
use Illuminate\Http\Request;
use Illuminate\Support\Arr;

class RepliesController extends Controller
{
    public function index(){
        $data = Reply::all();
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
        $pos = $request->input('idQuest');
        $pos=$pos+1;
        $data = Question::all();
        $size = $data->count();
           for ($i = 0; $i < $pos; $i++) {
            $need = $data[$i];
        }
        $id = $need->id;
        $reply = new Reply;
        $reply->message = $request->input('message');
        $reply->idUser = $request->input('idUser');
        $reply->idQuest = $id;
        $reply->save();
        return response()->json("Added Successfuly!");
    }
}
