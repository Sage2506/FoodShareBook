class IngredientSerializer < ActiveModel::Serializer
  attributes :id, :name, :description, :recipe
  # has_many :dishes
end
