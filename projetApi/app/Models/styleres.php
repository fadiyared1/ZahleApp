<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class styleres extends Model
{
    use HasFactory;
    public function restaurants()
    {
    //return $this->belongsToMany(RelatedModel, pivot_table_name, foreign_key_of_current_model_in_pivot_table, foreign_key_of_other_model_in_pivot_table);
  /*  return $this->belongsToMany(
            restaurants::class,
            'styleres_restaurant',
            'styleres_id',
            'restaurant_id');*/
    }
}
