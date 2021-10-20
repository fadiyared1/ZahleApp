<?php

namespace App\Http\Controllers;
use Illuminate\Support\Facades\DB;
use App\Models\User;
use Illuminate\Http\Request;

class UsersController extends Controller
{
    public function selectall(){
        //$data = DB::table('users')->all();
        $data = User::all();
         $resultArray = array();
        $tempArray = array();
        // Loop through each result
        foreach($data as $row)
         {
             //$data->password;
        // Add each result into the results array
        $tempArray = $row;
        array_push($resultArray, $tempArray);
         }
        // Encode the array to JSON and output the results
        echo json_encode($resultArray);
        }

        public function addnew(Request $request){
            $user = new User;
            $user->firstname = $request->input('firstname');
            $user->lastname = $request->input('lastname');
            $user->email = $request->input('email');
            $user->password = $request->input('password');
            $user->save();
            /*$response["user"] = $user;
            $response["success"] = 1;*/
            return response()->json("Added Successfuly!");
        }
        public function delete($id){
            $user = User::where('id', '=', $id)->first();
            $user->delete();
            return response()->json("Removed successfully!");
        }
}
