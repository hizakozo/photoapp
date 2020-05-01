select i.image_id as image_id, i.user_id as user_id, i.image_path as image_path, i.explanation as explanation,
       i.create_at as create_at
from image i
  join favorite_history f on i.image_id = f.image_id
where f.user_id = /* userId */1
order by i.create_at desc;