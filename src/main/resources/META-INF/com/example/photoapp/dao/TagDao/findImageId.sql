select tag.tag_id, tag
from tag
  join image_tag on tag.tag_id = image_tag.tag_id
where image_tag.image_id = /* imageId*/1;