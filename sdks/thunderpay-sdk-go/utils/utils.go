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

import "time"

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
