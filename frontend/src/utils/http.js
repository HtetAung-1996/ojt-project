import constant from "./constant";

async function post(path, body) {
  const resp = await fetch(constant.localDomain + path, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  });
  return resp;
}

async function postFormData(path, body) {
  const formData = new FormData();
  for (let key in body) {
    formData.append(key, body[key]);
  }
  const resp = await fetch(constant.localDomain + path, {
    method: "POST",
    body: formData,
  });
  return resp;
}

async function postMedia(path, file, fileType) {
  let formData = new FormData();
  formData.append("file", file);
  formData.append("fileType", fileType);
  const resp = await fetch(constant.localDomain + path, {
    method: "POST",
    body: formData,
  });
  return resp;
}

async function putMedia(path, file, fileType, filePath) {
  let formData = new FormData();
  formData.append("file", file);
  formData.append("fileType", fileType);
  formData.append("filePath", filePath);
  const resp = await fetch(constant.localDomain + path, {
    method: "PUT",
    body: formData,
  });
  return resp;
}

async function get(path, body) {
  const resp = await fetch(constant.localDomain + path, {
    method: "GET",
  });
  return resp;
}

async function put(path, body) {
  const resp = await fetch(constant.localDomain + path, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  });
  return resp;
}

async function putFormData(path, body) {
  const formData = new FormData();
  for (let key in body) {
    formData.append(key, body[key]);
  }
  const resp = await fetch(constant.localDomain + path, {
    method: "PUT",
    body: formData,
  });
  return resp;
}

async function del(path, body) {
  const resp = await fetch(constant.localDomain + path, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  });
  return resp;
}

export default {
  post,
  postMedia,
  putMedia,
  get,
  put,
  del,
  postFormData,
  putFormData,
};
