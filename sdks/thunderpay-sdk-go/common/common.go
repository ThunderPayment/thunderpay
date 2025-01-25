/**
 * @file common.go
 * @author Krisna Pranav
 * @brief common
 * @version 1.0
 * @date 2024-01-23
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package common

import (
	"context"
	"net/http"
	"net/url"
)

type IConfiguration interface {
	AddDefaultHeader(key string, value string)
	ServerURL(index int, variables map[string]string) (string, error)
	ServerURLWithContext(ctx context.Context, endpoint string) (string, error)
}

type IClient interface {
	GetConfig() IConfiguration
	PrepareRequest(
		ctx context.Context,
		path string, method string,
		postBody interface{},
		headerParams map[string]string,
		queryParams url.Values,
		formParams url.Values,
		formFiles []FormFile) (localVarRequest *http.Request, err error)
	CallAPI(request *http.Request) (*http.Response, error)
	Decode(v interface{}, b []byte, contentType string) (err error)
}

type FormFile struct {
	FileBytes    []byte
	FileName     string
	FormFileName string
}

type XenditSdkError struct {
	rawResponse  map[string]interface{}
	status       string
	errorCode    string
	errorMessage string
}

func (e XenditSdkError) Error() string {
	return e.errorMessage
}

func (e XenditSdkError) ErrorCode() string {
	return e.errorCode
}

func (e XenditSdkError) RawResponse() map[string]interface{} {
	return e.rawResponse
}
