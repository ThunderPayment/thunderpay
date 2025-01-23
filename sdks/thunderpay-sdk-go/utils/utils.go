/**
 * @file utils.go
 * @author Krisna Pranav
 * @brief utils
 * @version 1.0
 * @date 2024-01-23
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package utils

import (
	"bytes"
	"encoding/json"
	"fmt"
	"reflect"
	"time"
	"unicode/utf8"
)

func PtrBool(v bool) *bool {
	return &v
}

func PtrInt(v int) *int {
	return &v
}

func PtrInt32(v int32) *int32 {
	return &v
}

func PtrInt64(v int64) *int64 {
	return &v
}

func PtrFloat32(v float32) *float32 {
	return &v
}

func PtrFloat64(v float64) *float64 {
	return &v
}

func PtrString(v string) *string {
	return &v
}

func PtrTime(v time.Time) *time.Time {
	return &v
}

func NewStrictDecoder(data []byte) *json.Decoder {
	dec := json.NewDecoder(bytes.NewBuffer(data))
	return dec
}

func Strlen(s string) int {
	return utf8.RuneCountInString(s)
}

func NewError(s string) error {
	return fmt.Errorf(s)
}

func IsNill(i interface{}) bool {
	if i == nil {
		return true
	}

	switch reflect.TypeOf(i).Kind() {
	case reflect.Chan, reflect.Func, reflect.Map, reflect.Ptr, reflect.UnsafePointer, reflect.Interface, reflect.Slice:
		return reflect.ValueOf(i).IsNil()
	case reflect.Array:
		return reflect.ValueOf(i).IsZero()
	}

	return false
}

type MappedNullable interface {
	ToMap() (map[string]interface{}, error)
}
