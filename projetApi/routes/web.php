<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});
Route::POST('/user/new', [App\Http\Controllers\UsersController::class, 'addnew']);
Route::get('/user/delete/{id}', [App\Http\Controllers\UsersController::class, 'delete']);
Route::get('/users', [App\Http\Controllers\UsersController::class, 'selectall']);
Route::get('/home', [App\Http\Controllers\RestaurantsController::class, 'index']);
Route::get('/restaurants', [App\Http\Controllers\RestaurantsController::class, 'index']);
Route::get('/restaurants/{id}', [App\Http\Controllers\RestaurantsController::class, 'selectone']);
Route::get('/hotels', [App\Http\Controllers\HotelsController::class, 'index']);
Route::get('/hotels/{id}', [App\Http\Controllers\HotelsController::class, 'selectone']);
Route::get('/nights', [App\Http\Controllers\NightsController::class, 'index']);
Route::get('/nights/{id}', [App\Http\Controllers\NightsController::class, 'selectone']);
Route::get('/houses', [App\Http\Controllers\HousesController::class, 'index']);
Route::get('/houses/{id}', [App\Http\Controllers\HousesController::class, 'selectone']);
Route::get('/questions', [App\Http\Controllers\QuestionsController::class, 'index']);
Route::get('/question/{id}', [App\Http\Controllers\QuestionsController::class, 'questreplies']);
Route::POST('/question/new', [App\Http\Controllers\QuestionsController::class, 'addnew']);
Route::get('/replies', [App\Http\Controllers\RepliesController::class, 'index']);
Route::POST('/reply/new', [App\Http\Controllers\RepliesController::class, 'addnew']);

