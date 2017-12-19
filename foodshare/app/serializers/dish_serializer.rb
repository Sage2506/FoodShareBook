class DishSerializer < ActiveModel::Serializer
  attributes :id, :name, :description, :recipe
  # has_many :ingredients

  def ingredient_ids
    object.ingredients.map(&:id)
  end
end
