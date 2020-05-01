select image_id, user_id, image_path, explanation, create_at
from image
where user_id =
(
  select follow_user_id
  from friend_status
  where follower_user_id = /* userId */1) or user_id = /* myId */1
 order by image_id desc;