import constant from "./constant";

async function post(path, body) {
  const resp = await fetch(constant.localDomain + path, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  });
  return resp;
}

async function postImg(path, file, fileType) {
  let formData = new FormData();
  formData.append("poster", file);
  formData.append("fileType", fileType);
  const resp = await fetch(constant.localDomain + path, {
    method: "POST",
    body: formData,
    headers: {
      Accept: "application/json",
    },
  });
  return resp;
}

async function get(path, body) {
  const resp = await fetch(constant.localDomain + path, {
    method: "GET",
  });
  return resp;
}

export default { post, postImg, get };
