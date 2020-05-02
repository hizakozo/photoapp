select comment_id, image_id, user_id, comment, create_at
from comment
where image_id = /* imageId*/1
order by comment_id desc;