select image.image_id as image_id, user_id, image_path, explanation, image.create_at as create_at
from image
  join image_tag on image.image_id = image_tag.image_id
where image_tag.tag_id = /* tagId */1
order by image.image_id desc;