module Api
  module V1
    class IngredientsController < ApplicationController

      def index
        render json: Ingredient.all
      end

      def show
        render json: Ingredient.find(params[:id])
      end

      def create
        ingredient = Ingredient.new(ingredient_params)
        if ingredient.save
          render json: ingredient
        else
          render json: {status: 500, err: 'ingredient could not be saved.'}
        end
      end

      def update
        ingredient = Ingredient.find(params[:id])
        ingredient.update(ingredient_params)
        render json: ingredient
      end

      def destroy
        ingredient = Ingredient.find(params[:id])
        ingredient.destroy
        render json: {message: "successfully deleted!"}, status: 200
      end

      private

        def ingredient_params
          params.require(:ingredient).permit(:name, :breed, :temperament, :weight, :hobby_ids => [])
        end
    end
  end
end
